/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jas
 */
package one;
import java.util.*;
import TableModel.TaskModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public final class TimeLineChart extends ApplicationFrame{
    
    public static HashMap<Date,String> hm;// = new HashMap<>();
    public static String[] task;// = new String[hm.size()];
    public static Date[] time;// = new Date[hm.size()];
    String title;
    TaskModel tm; 
    
    public TimeLineChart(final String title) {

        super(title);
         //this.title = title;
        hm = new HashMap<>();
        tm = new TaskModel();
        hm = tm.timeLineChart(101);
        
        task = new String[hm.size()];
        time = new Date[hm.size()];
        
        
        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //this.add(chartPanel);
        setContentPane(chartPanel);
        
    }
    
    public static IntervalCategoryDataset createDataset() {
        
        
        final TaskSeries s1 = new TaskSeries("Scheduled");
        
        int i=0;
        for (Map.Entry<Date,String> pairs : TimeLineChart.hm.entrySet()) {
            time[i] =  (Date) pairs.getKey();
            task[i] = (String) pairs.getValue();
            s1.add(new Task(task[i], new SimpleTimePeriod(date(14, Calendar.OCTOBER, 2014),
                                    time[i])));
            i = i+1;
        }
        System.out.println(task[1]);
        s1.add(new Task(task[1], new SimpleTimePeriod(date(14, Calendar.OCTOBER, 2014),
                                    time[1])));
        s1.add(new Task("Database set up",
                    new SimpleTimePeriod(date(15, Calendar.OCTOBER, 2014),
                                    date(30, Calendar.OCTOBER, 2014))));
        //}                    
//            s1.add(new Task("Obtain Approval",
//                    new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
//                            date(9, Calendar.APRIL, 2001))));
//            s1.add(new Task("Requirements Analysis",
//                    new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
//                            date(5, Calendar.MAY, 2001))));
//            s1.add(new Task("Design Phase",
//                    new SimpleTimePeriod(date(6, Calendar.MAY, 2001),
//                            date(30, Calendar.MAY, 2001))));
//            s1.add(new Task("Design Signoff",
//                    new SimpleTimePeriod(date(2, Calendar.JUNE, 2001),
//                            date(2, Calendar.JUNE, 2001))));
//            s1.add(new Task("Alpha Implementation",
//                    new SimpleTimePeriod(date(3, Calendar.JUNE, 2001),
//                            date(31, Calendar.JULY, 2001))));
//            s1.add(new Task("Design Review",
//                    new SimpleTimePeriod(date(1, Calendar.AUGUST, 2001),
//                            date(8, Calendar.AUGUST, 2001))));
//            s1.add(new Task("Revised Design Signoff",
//                    new SimpleTimePeriod(date(10, Calendar.AUGUST, 2001),
//                            date(10, Calendar.AUGUST, 2001))));
//            s1.add(new Task("Beta Implementation",
//                    new SimpleTimePeriod(date(12, Calendar.AUGUST, 2001),
//                            date(12, Calendar.SEPTEMBER, 2001))));
//            s1.add(new Task("Testing",
//                    new SimpleTimePeriod(date(13, Calendar.SEPTEMBER, 2001),
//                            date(31, Calendar.OCTOBER, 2001))));
//            s1.add(new Task("Final Implementation",
//                    new SimpleTimePeriod(date(1, Calendar.NOVEMBER, 2001),
//                            date(15, Calendar.NOVEMBER, 2001))));
//            s1.add(new Task("Signoff",
//                    new SimpleTimePeriod(date(28, Calendar.NOVEMBER, 2001),
//                            date(30, Calendar.NOVEMBER, 2001))));
       final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection; 
    }
        
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
    
       private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "TimeLine Chart Demo",  // chart title
            "Task",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;    
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final TimeLineChart demo = new TimeLineChart("TimeLine Chart Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
////        System.out.println(demo.hm);
////        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
////	String date = sdf.format(new Date("Sat Oct 25 00:00:00 PDT 2014")); 
////	System.out.println(date); //15/10/2013
////        Date result = demo.date(25, 10, 2014);
////        System.out.println(result); 
        String[] task = new String[TimeLineChart.hm.size()];
        int i=0;
        for (Map.Entry pairs : TimeLineChart.hm.entrySet()) {
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            task[i] = (String) pairs.getValue();
            
//            Date d = (Date) pairs.getKey();
//            System.out.println(d);
//            System.out.println(d.getMonth());
//            System.out.println(d.getYear());
            i += 1;
        }
        System.out.println(task[0]);
    }
        
        
        
        
}
