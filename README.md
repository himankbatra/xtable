# xtable [![CircleCI](https://circleci.com/gh/himankbatra/xtable.svg?style=svg&circle-token=3c690ed73165d2c5d248de0a71f645b736cb48f0)](https://circleci.com/gh/himankbatra/xtable) [![codecov](https://codecov.io/gh/himankbatra/xtable/branch/master/graph/badge.svg?token=XrjXEn3aq0)](https://codecov.io/gh/himankbatra/xtable)

xtable is a Java library for rendering the data in Table with Horizontal or Vertical Layout.

## Getting Started

To use xtable in your application, you have to add xtable to your classpath.You just need to add dependency in your
 favorite build tool as shown below.

For Apache Maven users, please add following to your pom.xml.

```
<dependencies>
   <dependency>
            <groupId>com.xebia</groupId>
            <artifactId>xtable</artifactId>
            <version>0.1.0</version>
        </dependency>
</dependencies>
```

Gradle users can add following to their build.gradle file.

```
compile(group: 'com.xebia', name: 'xtable', version: '0.1.0')
```

## Usage

```
import com.xebia.xtable.Table; // import xtable package


  Table table = new Table.Builder()
                         .withNumberOfRows(3) // enter total number of rows
                         .withNumberOfColumns(2) // enter total number of columns
            			 .withColumnWidth(10,20) // enter column widths of each column
                         .withHeaderRow("Sno.","Name").build(); // enter header row  

  table.insertRow("1","himank")
       .insertRow("2","akash").create(); // insert rows data and then create table

  table.render(); // render created table

  System.out.println("Shape of the table is "+table.shape()); // print shape of table
```

## Output

```

+------------+----------------------+
| Sno.       | Name                 |
+------------+----------------------+
| 1          | himank               |
+------------+----------------------+
| 2          | akash                |
+------------+----------------------+

Shape of the table is 2x3

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.