package org.wso2.streamingml;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class DataLoader {
    private String fileName;
    private static Splitter splitter = Splitter.on(',');
    private LinkedBlockingQueue<Object> eventBufferList;
    private BufferedReader br;
    private int count;
    private long eventLimit;//This many events will be read from the stream data set

    public DataLoader(String fileName, long eventCount) {
        this.fileName = fileName;
        eventBufferList = new LinkedBlockingQueue<Object>();
        this.eventLimit = eventCount;
    }

    public void runSingleStream() {
        try {
            br = new BufferedReader(new FileReader(fileName), 10 * 1024 * 1024);
            String line = br.readLine();

            while (line != null) {
                //We make an assumption here that we do not get empty strings due to missing values that may present in the input data set.
                Iterator<String> dataStrIterator = splitter.split(line).iterator();
                String t = dataStrIterator.next();
                String a1 = dataStrIterator.next();
                String b1 = dataStrIterator.next();
                String c1 = dataStrIterator.next();
                String d1 = dataStrIterator.next();
//                String e1 = dataStrIterator.next();
//                String f1 = dataStrIterator.next();
//                String g1 = dataStrIterator.next();
//                String h1 = dataStrIterator.next();
//                String j1 = dataStrIterator.next();
//                String k1 = dataStrIterator.next();
//                String l1 = dataStrIterator.next();

                Object[] eventData = null;
                //Convert time stamp to long value
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
                Date dt = df.parse(t);
                Long l = dt.getTime();

                try {
                    eventData = new Object[]{

                            Double.parseDouble(a1),
                            Double.parseDouble(b1),
                            Double.parseDouble(c1),
                            (d1)
//                            Double.parseDouble(e1),
//                            Double.parseDouble(f1),
//                            Double.parseDouble(g1),
//                            Double.parseDouble(h1),
//                            Double.parseDouble(j1),
//                            Double.parseDouble(k1),
//                            (l1)
                    };
                } catch (NumberFormatException e) {
                    line = br.readLine();
                    continue;
                }

                //We keep on accumulating data on to the event queue.
                //This will get blocked if the space required is not available.
                eventBufferList.put(eventData);
                line = br.readLine();
                count++;

                if (count >= eventLimit) {
                    break;
                }
            }
            //System.out.println("Total amount of events read : " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public LinkedBlockingQueue<Object> getEventBuffer() {
        return eventBufferList;
    }
}