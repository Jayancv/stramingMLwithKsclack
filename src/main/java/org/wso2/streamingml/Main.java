//package org.wso2.streamingml;
//
//import org.wso2.siddhi.core.ExecutionPlanRuntime;
//import org.wso2.siddhi.core.SiddhiManager;
//import org.wso2.siddhi.core.event.Event;
//import org.wso2.siddhi.core.stream.input.InputHandler;
//import org.wso2.siddhi.core.stream.output.StreamCallback;
//import org.wso2.siddhi.core.util.EventPrinter;
//
//import java.util.Iterator;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String inputStream = " define stream inputStream (attribute_0 double, attribute_1 double, " +
//                "attribute_2 double, attribute_3 double);";
//
//        String query = "@info(name = 'query2') from inputStream#streamingml:streamingRegressionSamoa(4,attribute_0, attribute_1 , attribute_2 ,attribute_3) " +
//                "select att_0 as attribute_0, att_1 as attribute_1,att_2 as attribute_2,prediction as prediction insert into outputStream;";
//
//        String outputStream = " define stream outputStream (attribute_0 double, attribute_1 double,attribute_2 double, prediction double );";
//        SiddhiManager siddhiManager = new SiddhiManager();
//
//        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(
//                inputStream + outputStream + query);
//        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
//        executionPlanRuntime.addCallback("outputStream", new StreamCallback() {
//            @Override
//            public void receive(Event[] events) {
//                for (Event event : events) {
//                    EventPrinter.print(events);
//                }
//            }
//        });
//
//        DataLoader inputData = new DataLoader("3dNetwork.csv", 60000000);
//        inputData.runSingleStream();
//        LinkedBlockingQueue<Object> queueData = inputData.getEventBuffer();
//        Iterator<Object> itrator = queueData.iterator();
//
//        while (itrator.hasNext()) {
//            try {
//                Object[] obj = (Object[]) itrator.next();
//                inputHandler.send(obj);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}



package org.wso2.streamingml;

import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {

        String inputStream = " define stream inputStream (attribute_0 double, attribute_1 double, " +
                "attribute_2 double, attribute_3 string);";

        String query = "@info(name = 'query2') from inputStream#streamingml:streamingClassificationSamoa(4,2,0,'',500,attribute_0, attribute_1 , attribute_2 ,attribute_3) " +
                "select att_0 as attribute_0, att_1 as attribute_1,att_2 as attribute_2,prediction as prediction insert into outputStream;";

//        String query = "@info(name = 'query2') from inputStream " +
//                "select attribute_0,  attribute_1,attribute_2,attribute_3 insert into outputStream;";

        String outputStream = " define stream outputStream (attribute_0 double, attribute_1 double,attribute_2 double, prediction string );";
        SiddhiManager siddhiManager = new SiddhiManager();

        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(
                inputStream + outputStream + query);
        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.addCallback("outputStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                    EventPrinter.print(events);

            }
        });

        DataLoader inputData = new DataLoader("skin.csv", 60000000);
        inputData.runSingleStream();
        LinkedBlockingQueue<Object> queueData = inputData.getEventBuffer();
        Iterator<Object> itrator = queueData.iterator();

        while (itrator.hasNext()) {
            try {
                Object[] obj = (Object[]) itrator.next();
                inputHandler.send(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


//package org.wso2.streamingml;
//
//import org.wso2.siddhi.core.ExecutionPlanRuntime;
//import org.wso2.siddhi.core.SiddhiManager;
//import org.wso2.siddhi.core.event.Event;
//import org.wso2.siddhi.core.stream.input.InputHandler;
//import org.wso2.siddhi.core.stream.output.StreamCallback;
//import org.wso2.siddhi.core.util.EventPrinter;
//
//import java.util.Iterator;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String inputStream = " define stream inputStream (attribute_0 double, attribute_1 double, " +
//                "attribute_2 double, attribute_3 double);";
//
//        String query = "@info(name = 'query2') from inputStream#streamingml:streamingClusteringSamoa(2,attribute_0, attribute_1 , attribute_2 ,attribute_3) " +
//                "select center0 as center0,center1 as center1 insert into outputStream;";
//
//        String outputStream = " define stream outputStream (center0 string,center1 string );";
//        SiddhiManager siddhiManager = new SiddhiManager();
//
//        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(
//                inputStream + outputStream + query);
//        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
//        executionPlanRuntime.addCallback("outputStream", new StreamCallback() {
//            @Override
//            public void receive(Event[] events) {
//                EventPrinter.print(events);
//            }
//        });
//
//        DataLoader inputData = new DataLoader("3dNetwork.csv", 6000000);
//        inputData.runSingleStream();
//        LinkedBlockingQueue<Object> queueData = inputData.getEventBuffer();
//        Iterator<Object> itrator = queueData.iterator();
//
//        while (itrator.hasNext()) {
//            try {
//                Object[] obj = (Object[]) itrator.next();
//                inputHandler.send(obj);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

