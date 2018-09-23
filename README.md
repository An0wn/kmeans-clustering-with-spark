kmeans spark
================

KMeans Clustering with Apache Spark
-----------------------------------

Here we are going to use **Spark** to perform **KMeans Clustering** on [Wholesale customer data](https://archive.ics.uci.edu/ml/machine-learning-databases/00292/) ...

``` bash
## run the application from command line
$ cd app/path

## Then we create a JAR package containing the application’s code,
## then use the spark-submit script to run our program ...

$ sbt package

## wait until it finishes, then ...

$ spark-submit \
--class KMeansCustomers \
--master local[2] \
target/scala-2.10/kmeanscustomers_2.10-1.0.jar

// initialize everything:
// reading the file excluding the header and converting the data to Double
// scale data

// sample data to determine the number of clusters

cluster cost on sample data

(1,1010.3488484823686)
(2,699.7397005938287)
(3,516.1369832728788)
(4,468.2960315986219)
(5,294.84034750116405)
(6,259.0140817840747)
(7,204.4830504989239)
(8,176.57523870971073)

// construct the model with 3 Ks

// model cost
Total cost 2243.4742774439815

clusters centers
[-0.6403161029271415,-0.05152236081404646,0.12352033298042772,-0.33590176218991785,-0.42193407246966297,0.12434960976610163,-0.4375022668956516,-0.09087426696022169]
[1.4470045000093716,0.11165037776272521,-0.2927019425813138,0.7181075351460804,0.9406089241191811,-0.33107433476877784,0.9892957054018908,0.082711598982715]
[-0.6895122114138625,0.5899966895017635,1.964581022427317,5.1696184610141795,1.2857532746879967,6.892753824890136,-0.5542310929772069,16.459711293240822]
```
