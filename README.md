# Test
SayariLabs Test Solution:

(A) Development Environment: Ubuntu Linux: 20.04, Intellij 2022, SBT, Spark version: 3, Scala version:2.12.12 and Java SDK version: 11.

(B) Task Stage 1: Extract and structure (json) data for fast data loading into big data storage part of (ELT/ETL) process.

(C) Artifacts included: SayariLabs.jar and two jsonl files in (data_source) directory.

(D) Task Stage 2 (TBD): Data Transformation: Fix null values, incomplete data, de-duplication of duplicates. (using Regex, SQL or Pandas dataframes)

(E) Entity and Individual data output folders should be in (Test) folder when (SayariLabs.jar) complete processing the data.

Step by step instruction:

1. Please clone or download the zip file that contains (SayariLabs.jar file and data_source folder) into a local (Test) directory on a computer: 
 
   ![image](https://user-images.githubusercontent.com/106841986/171950523-71c278ea-47e5-4f02-9940-cb6e220f3905.png)
 
2. Please make sure (data_source) folder in (Test) folder have two jsonl files:

   ![image](https://user-images.githubusercontent.com/106841986/171950753-d7bfe74b-a16f-45fa-b990-aab6d9260536.png)
 
3. From the (Test) directory, type the (path-to-spark-submit) file, plus the (arguments --class ... ) and press (Enter):
   
   $>~/spark-3.2.0-bin-hadoop3.2/bin/spark-submit   --class com.justice.ike.task.ExtractLoad --master local[*] SayariLabs.jar 
  ![image](https://user-images.githubusercontent.com/106841986/171951026-e46f26da-6c3f-4ef8-8e2a-54c3f922b3bf.png)

4. Logging for warning should appear: Logging was enabled on purpose incase (jsonl) files are missing.
   ![image](https://user-images.githubusercontent.com/106841986/171953955-4998b373-a04a-4957-a6b5-c0cbec35315a.png)

5. After few seconds, the program will start to show data for each section of the tasks on the screen: 

   ![image](https://user-images.githubusercontent.com/106841986/171954395-82ab207d-1117-4157-9b42-b5a017349816.png)

   . .......

6. Entity data output: 

   ![image](https://user-images.githubusercontent.com/106841986/171954522-89d30096-2cc5-4170-89d2-08786258d948.png)

7. Individual data output: 

   ![image](https://user-images.githubusercontent.com/106841986/171965470-6df170dc-f1b2-43ec-94a0-9fa3cc9b667e.png)

 
8.  [Success] Total time: 

     ![image](https://user-images.githubusercontent.com/106841986/171955282-eebb47e3-600f-46b7-9232-47c3c8b2723b.png)
     
9. Data output folders:

   ![image](https://user-images.githubusercontent.com/106841986/171967151-0bdd1322-8f02-4af2-a0ff-96fc15d1ae9f.png)









