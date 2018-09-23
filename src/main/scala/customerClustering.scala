
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.clustering.KMeansModel

object KMeansCustomers extends App {

  println("// initialize everything:")
  val conf = new SparkConf().setAppName("KMeansCustomers").setMaster("local[2]")
  val sc = new SparkContext(conf)

  println("// reading the file excluding the header and converting the data to Double")
  val data = sc.textFile("Wholesale customers data.csv").
    filter(line => !line.contains("Channel")).
    map(line => {
      val array = line.split(",")
      Vectors.dense(array.map(_.toDouble))
    })

  println("// scale data")
  val scaler = new StandardScaler(withMean = true, withStd = true).fit(data)
  val scaledData = scaler.transform(data).cache()

  println("")
  println("// sample data to determine the number of clusters")
  val sampleData = scaledData.sample(false, 0.2).cache()
  val clCost = (1 to 8).map { clusters =>
    val kmeans = new KMeans()
      .setK(clusters)
      .setMaxIterations(5)
      .setInitializationMode(KMeans.K_MEANS_PARALLEL) // KMeans||
    val model = kmeans.run(sampleData)
    (clusters, model.computeCost(sampleData))
  }
  println("")
  println ("cluster cost on sample data " )
  println("")
  clCost.foreach(println)

  println("")
  println("// construct the model with 3 Ks")
  println("")
  val kmeans = new KMeans()
    .setK(3)
    .setMaxIterations(5)
    .setInitializationMode(KMeans.K_MEANS_PARALLEL) //KMeans||
  val model = kmeans.run(scaledData)
  println("// model cost")
  println("Total cost " + model.computeCost(scaledData))
  println("")
  printClusterCenters(model)
  println("")

  def printClusterCenters(model:KMeansModel) {
    val clCenters: Array[Vector] = model.clusterCenters
    println("clusters centers")
    clCenters.foreach(println)
  }

}
