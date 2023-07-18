// 顯示推播通知
function showNotification(message) {
    // 創建新的通知元素
    let notification = document.createElement('div');
    notification.className = 'notification-message';
    notification.innerText = message;

    // 創建新的容器元素
    let notificationContainer = document.createElement('div');
    notificationContainer.className = 'notification-container';
    notificationContainer.appendChild(notification);

    // 將通知容器元素添加到 body
    document.body.appendChild(notificationContainer);

    // 三秒後移除通知容器元素
    setTimeout(function() {
        document.body.removeChild(notificationContainer);
    }, 3000);
}

function websocketInit() {
    const socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function() {
        console.log('WebSocket連接成功！');

        stompClient.subscribe('/topic/B2F', function(message) {
            let response = message.body;
            showNotification(response);
        });

    });
}

function sendMsg(msg) {
    // 透過 stompClient.send 向/app/F2B 後端傳送消息, 藉由 spring boot @messageMapping 中定義的(/app 為前綴）
    const destination = '/app/F2B';
    stompClient.send(destination, {}, msg);
}