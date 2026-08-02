sơ đồ kiến trúc
sơ đồ cấu trúc +----------------------+ | Client | | (Postman/Browser) | +----------+-----------+ |
| HTTP Request | v +---------------------------+ | API Gateway | | Logging Global Filter |
| Port: 8080 | +------------+--------------+ | +----------------+----------------+ | |
| Service Discovery (lb://...) | | | v v +-----------+ +--------------+ | Eureka |<---------------->| OrderService | 
| Server | | Port:8081 | | Port:8761 | +------+-------+ +-----------+ | | OpenFeign (Service ID) | v +--------+--------+ | InventoryService|
| Port:8082 | +--------+--------+ | | Query Product | v +----------------+ | product table | | inventory_db | +----------------+

Câu 1: 
Service Discovery đơn giản là mỗi service tự đăng ký vào Eureka lúc start (tên, ip, port), rồi cứ tầm 30s gửi heartbeat để báo còn sống. Muốn gọi service khác thì chỉ cần biết tên (service-id) là Eureka trả về địa chỉ đang chạy, không cần biết IP thật.

Nếu Gateway ghi cứng IP thì:

Container restart là đổi IP, config cũ hỏng ngay.
Chạy nhiều bản (instance) của 1 service thì không biết ghi IP nào, còn dùng tên service qua Eureka thì tự load balance được hết.
Hạ tầng đổi là phải sửa code Gateway, bất tiện.
Instance chết mà Gateway không biết, cứ forward request vào chỗ chết -> lỗi cho client.

Câu 2: 
Gateway đang route bằng tên service (lb://order-service) chứ không phải IP nên không cần đụng gì tới Gateway hết:

1.Start thêm vài bản Order Service ở port khác, giữ nguyên application name là order-service.
2.Mấy bản mới tự đăng ký vào Eureka, gộp chung danh sách với bản cũ.
3.Load balancer ở Gateway tự thấy danh sách mới và chia request cho tất cả các bản đang chạy, không cần sửa config hay restart Gateway.
4.Nói chung cứ chạy thêm instance, đặt tên service giống nhau là Gateway tự nhận và chia tải, không phải đụng gì tới cấu hình cũ.
