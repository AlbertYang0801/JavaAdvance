package com.albert.javase.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 文件监听
 */
public class NIO2WatchService {

    public static Map<String,WatchService> WATCH_KEY_CACHE = new ConcurrentHashMap<>();

    //WatchService 是线程安全的，跟踪文件事件的服务，一般是用独立线程启动跟踪
    public void watchRNDir(Path path) throws IOException, InterruptedException {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            //给path路径加上文件观察服务
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
            String id= "123";
            WATCH_KEY_CACHE.put(id,watchService);
            // start an infinite loop
            while (true) {
                if(!WATCH_KEY_CACHE.containsKey(id)){
                    System.out.println("任务取消");
                    break;
                }
                System.out.println("尝试获取结果");
                // retrieve and remove the next watch key
                final WatchKey key = watchService.poll(1, TimeUnit.SECONDS);
                if(Objects.isNull(key)){
                    continue;
                }
                // get list of pending events for the watch key
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    // get the kind of event (create, modify, delete)
                    final Kind<?> kind = watchEvent.kind();
                    // handle OVERFLOW event
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    //创建事件
                    if(kind == StandardWatchEventKinds.ENTRY_CREATE){

                    }
                    //修改事件
                    if(kind == StandardWatchEventKinds.ENTRY_MODIFY){

                    }
                    //删除事件
                    if(kind == StandardWatchEventKinds.ENTRY_DELETE){

                    }
                    // get the filename for the event
                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                    final Path filename = watchEventPath.context();
                    // print it out
                    System.out.println(kind + " -> " + filename);

                }
                // reset the keyf
                boolean valid = key.reset();
                // exit loop if the key is not valid (if the directory was
                // deleted, for
                if (!valid) {
                    break;
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(()->{
            final Path path = Paths.get("/tmp");
            NIO2WatchService watch = new NIO2WatchService();
            try {
                watch.watchRNDir(path);
            } catch (IOException | InterruptedException ex) {
                System.err.println(ex);
            }
        }).start();
        Thread.sleep(10000L);
        WatchService watchService = WATCH_KEY_CACHE.get("123");
        WATCH_KEY_CACHE.remove("123");
        watchService.close();
    }


}