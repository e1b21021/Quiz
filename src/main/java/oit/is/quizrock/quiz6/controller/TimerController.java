package oit.is.quizrock.quiz6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class TimerController {

    private LocalDateTime startTime;
    private static final long TIMER_DURATION_SECONDS = 10;

    // タイマーを開始するエンドポイント
    @GetMapping("/start-timer")
    @ResponseBody
    public String startTimer() {
        this.startTime = LocalDateTime.now();  // 現在の時間を設定してリセット
        return "タイマーが開始されました";
    }

    // 残り時間を返すエンドポイント
    @GetMapping("/remaining-time")
    @ResponseBody
    public long getRemainingTime() {
        if (startTime == null) {
            return TIMER_DURATION_SECONDS;
        }
        long elapsed = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
        return TIMER_DURATION_SECONDS - elapsed > 0 ? TIMER_DURATION_SECONDS - elapsed : 0;
    }

    // タイマーのページを表示するエンドポイント
    @GetMapping("/timer")
    public String showTimerPage() {
        return "timer"; // `timer.html`を表示
    }
}
