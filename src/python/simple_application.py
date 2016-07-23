
# Submit to YARN usign
# spark-submit --master yarn-cluster simple_application.py

from pyspark import SparkConf, SparkContext

if __name__ == "__main__":

    conf = SparkConf()
    sc = SparkContext(appName="Simple Application", conf=conf)

    rdd = sc.parallelize(range(1, 10), 1)

    print(rdd.collect())

    print(rdd.sum())

    sc.stop()

    print("End")