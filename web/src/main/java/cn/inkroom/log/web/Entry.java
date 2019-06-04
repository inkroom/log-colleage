package cn.inkroom.log.web;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 入口启动类
 *
 * @author 墨盒
 * @date 19-2-1
 */
public class Entry {
    public static final int PORT = 2656;

    // web访问的根路径http://ip:port/，相当于项目名,/即忽略项目名
    public static final String CONTEXT = "/";

    //FIXME: 2019-2-7 目录结构有问题，开发模式可以加上web，打包的时候注意测试是否可以
    private static final String DEFAULT_WEBAPP_PATH = "web/src/main/webapp";

    public static Server createServerIn(int port) {

//        System.out.println(Object.class.getResource("/"));
//        System.out.println(Entry.class.getClassLoader().getResource(".").getPath());

        // 创建Server
        Server server = new Server(port);
        // 添加ThreadPool
//        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
//        queuedThreadPool.setName("queuedTreadPool");
//        queuedThreadPool.setMinThreads(10);
//        queuedThreadPool.setMaxThreads(200);
//        service.setThreadPool(queuedThreadPool);
//        // 添加Connector
//        SelectChannelConnector connector = new SelectChannelConnector();
//        connector.setPort(port);
//        connector.setAcceptors(4);// 同时监听read事件的线程数
//        connector.setMaxBuffers(2048);
//        connector.setMaxIdleTime(10000);
//        service.addConnector(connector);

        WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, CONTEXT);
        webContext.setDescriptor(DEFAULT_WEBAPP_PATH + "/WEB-INF/web.xml");
        webContext.setResourceBase(DEFAULT_WEBAPP_PATH);
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);
        return server;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        DOMConfigurator.configure(Thread.currentThread().getContextClassLoader()
//                .getResource("log4j.xml"));
        Server server = createServerIn(PORT);
        server.stop();
        server.start();
//        service.join();
    }
}