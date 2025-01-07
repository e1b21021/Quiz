let socket = new SockJS('/ws/battle');
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
  console.log('Connected: ' + frame);

  // クイズ更新用のリスナー
  stompClient.subscribe('/topic/battle', function (update) {
    const data = JSON.parse(update.body);
    document.getElementById('quiz').innerText = data.quiz;
    document.getElementById('cate').innerText = "カテゴリー: " + data.cate;
    document.getElementById('subcate').innerText = "サブカテゴリー: " + data.subcate;
  });

  // スコア更新用のリスナー
  stompClient.subscribe('/topic/score', function (score) {
    document.getElementById('opponentScore').innerText = score.body;
  });
});

// クイズを送信
function sendQuizUpdate(message) {
  stompClient.send("/app/battle/update", {}, JSON.stringify(message));
}

// スコアを送信
function sendScoreUpdate(score) {
  stompClient.send("/app/battle/score", {}, score);
}
