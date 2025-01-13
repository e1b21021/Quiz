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

  // 正解・不正解のメッセージ処理
  stompClient.subscribe('/topic/result', function (result) {
    const data = JSON.parse(result.body);
    if (data.isCorrect) {
      document.getElementById('resultMessage').innerText = "あなたは正解しました！";
    } else {
      document.getElementById('resultMessage').innerText = "あなたは不正解です！";
    }
  });
});

// クイズを送信
function sendQuizUpdate(message) {
  stompClient.send("/app/battle/update", {}, JSON.stringify(message));
}

// スコアを送信
function sendScoreUpdate(score) {
  stompClient.send("/app/battle/score", {}, JSON.stringify(score));
}

// 回答送信
function submitAnswer(userAnswer, userId) {
  const message = {
    userAnswer: userAnswer,
    userId: userId,
    // 他に必要な情報（例えばユーザーIDやクイズIDなど）を追加
  };
  stompClient.send("/app/battle/submit", {}, JSON.stringify(message));
}
