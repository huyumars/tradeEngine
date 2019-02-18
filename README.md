# tradeEngine
tiny trade engine

简易订单撮合系统：
1.	支持多种货币的汇率转换
2.	可以接受订单，包括市价/限价订单的买入和卖出。
3.	按照价格，时间的顺序匹配撮合订单
4.	支持订单的撤销和修改。

系统使用spring boot 进行Service的资源的创建与管理，利用JPA进行DB操作，利用Quartz框架实现交易的tick。Service主要分为三个部分：OrderService，主要的对外接口，实现订单的接收，更改与撤销。TradeService，交易引擎服务，负责订单的撮合。

OrderService: 负责和外部通信的主要Service。有三个接口：
1.	addOrder，派送新的订单，检查合法性，然后交付交易系统进行交易
2.	updateOrder，更新订单，查找系统内是否包含此id的订单，如果包含，进行更新：现将原油订单进行撤销。然后发送更新后的订单。
3.	cancelOrder，撤销已有的订单
