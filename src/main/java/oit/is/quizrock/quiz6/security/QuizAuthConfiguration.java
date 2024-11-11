package oit.is.quizrock.quiz6.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class QuizAuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/sample3/**"))
            .authenticated() // /sample3/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/quiz/**"))
            .authenticated() // /sample4/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .permitAll())// 上記以外は全員アクセス可能
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/p@ss,user2/p@ss,admin/p@ss
    UserDetails user1 = User.withUsername("あきら")
        .password("{bcrypt}$2y$05$HPzReJkJfxAKDKaaQtg3rOHaXZoSPqL3zJ/1.GH5W1BPjNtoHRzhS").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$VRnQ8qmQ4NnJKTk8dqy5/.yg6jZsmJ/6jeoRzoTtqWjfgtQ/Lyysu").roles("USER").build();

    UserDetails user3 = User.withUsername("ほんだ")
        .password("{bcrypt}$2y$05$v/qRt.YxpL.KxMJ/qk8/DuA3QMIIZUK4v7iFn4yOO/BC.gopfWEZK").build();

    UserDetails user4 = User.withUsername("いがき")
        .password("{bcrypt}$2y$05$GTRq.teM4/HEZzNAMyIR0u8JwgI/qY2hj2yFkU9C4Cn5ZYbK5L0HW").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2, user3, user4);
  }

}