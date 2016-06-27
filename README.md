There are three base custom charts:

1.Line Graph Chart

2.Histogram Chart

3.Pie Chart

Each of them is very easy for us to use. The project catalog has 2 branches:

com.chart.xiao.xiaochart

com.chart.xiao.sample

The first package is the main one which storages the custom chart views, the other is the samples one. If you want to use charts directly, you can read the samples. There are some functions we can use for our development.

The base class has its own functions for the children views, such as:

1.public void addMaxAndMinY(int maxY,int minY){}  //the y axis max num and min num, in order to calculate coordinate

2.public void addDateX(List<String> dateX){}  //add the x axis coordinate

3.public boolean isVerticalBackVisible(){}

4.public void setVerticalBackVisible(boolean verticalBackVisible){}

5.public boolean isHorizontalBackVisible(){}

6.public void setHorizontalBackVisible(boolean horizontalBackVisible){}

//the top four functions are used to operate wheather the background horizontal and vertical lines display

7.public boolean isRemoveXY(){}

8.public void setRemoveXY(boolean removeXY){}

//the top two functions are used to operate wheather the coordinate system displays

9.public abstract void addDatas(List datas);  //an abstract function, used to add the datas

If you say that you would like to learn something about how to develop and run the custom chart view, ok, you could read the xiaochart package¡¯s codes. LineGraphView.java, HistogramView.java, PieChartView.java are three children views. BaseView.java is the base view.

At present, there are only three custom views. We will develop it over and over again. 