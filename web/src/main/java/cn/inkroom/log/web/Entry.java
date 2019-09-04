/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.web;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

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
    private static String DEFAULT_WEBAPP_PATH = null;

    static {
        try {
            DEFAULT_WEBAPP_PATH = new ClassPathResource("webapp").getURI().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server createServerIn(int port) {

//        System.out.println(Object.class.getResource("/"));
//        System.out.println(Entry.class.getClassLoader().getResource("./").getPath());
//        try {
//            System.out.println(new ClassPathResource("webapp").getURI().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

        WebAppContext webContext = new WebAppContext();
        webContext.setContextPath(CONTEXT);
        webContext.setDescriptor(DEFAULT_WEBAPP_PATH + "/WEB-INF/web.xml");
        webContext.setResourceBase(DEFAULT_WEBAPP_PATH);

//        webContext.setExtraClasspath("./");
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);


//        ContextHandler contextHandler = new ContextHandler("/index");
//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setDirectoriesListed(true);
//        //   log.info(Application.class.getClassLoader().getResource("template").getPath());
////            resourceHandler.setResourceBase(Objects
////                    .requireNonNull(Application.class.getClassLoader().getResource("template")).getPath());
//        resourceHandler.setBaseResource(Resource.newResource(Application.class.getClassLoader().getResource("template")));
//        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
//        contextHandler.setHandler(resourceHandler);
//        server.setHandler(contextHandler);


        return server;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        DOMConfigurator.configure(Thread.currentThread().getContextClassLoader()
//                .getResource("log4j.xml"));

        Server server = createServerIn(PORT);
        server.stop();
        System.out.println("开始server");
        server.start();
//        service.join();
    }
}