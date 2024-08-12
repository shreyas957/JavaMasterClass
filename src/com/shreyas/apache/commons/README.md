# Apache Commons Libraries Overview
Apache Commons is a project by the Apache Software Foundation, aimed at providing a collection of reusable Java components. This README focuses on three of these libraries: Commons Lang3, Commons Collections4, and Commons Math3 and many more. These libraries offer additional functionality beyond what is available in the standard Java Development Kit (JDK), simplifying various programming tasks.

## Apache Commons Lang3

### Overview

Apache Commons Lang3 enhances the `java.lang` package, offering utilities for string manipulation, number handling, reflection, and concurrency, among others.

### Why Use Apache Commons Lang3?

- **String Manipulation**: Simplifies operations such as trimming, splitting, and capitalizing strings.
- **Number Utilities**: Provides convenient methods for number conversions and range checks.
- **Reflection**: Facilitates the use of reflection for class inspections and manipulation.
- **Concurrency**: Offers utilities to work with Java's concurrency API more efficiently.

### Example Usage

```java
import org.apache.commons.lang3.StringUtils;

public class Lang3Example {
    public static void main(String[] args) {
        String str = "  apache commons  ";
        boolean isEmpty = StringUtils.isEmpty(str); // true
        String capitalized = StringUtils.capitalize(str); // "Apache commons"

        System.out.println(isEmpty);
        System.out.println(capitalized);
    }
}
```

## Apache Commons Collections4

### Overview

Apache Commons Collections4 extends Java's Collection Framework, providing new data structures and utilities for collection manipulation.

### Why Use Apache Commons Collections4?

- **Enhanced Collections**: Introduces new collection types like bidirectional maps and ordered maps.
- **Utility Functions**: Offers utilities for collection manipulation, enhancing efficiency and reducing verbosity.

### Example Usage

```java
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class Collections4Example {
    public static void main(String[] args) {
        BidiMap<String, String> bidiMap = new TreeBidiMap<>();
        bidiMap.put("ONE", "1");
        bidiMap.put("TWO", "2");
        
        String key = bidiMap.getKey("1"); // "ONE"
        String value = bidiMap.get("TWO"); // "2"
        
        System.out.println(key);
        System.out.println(value);
    }
}
```

## Apache Commons Math3

### Overview

Apache Commons Math3 offers a suite of self-contained mathematics and statistics components that address common problems not available in the JDK.

### Why Use Apache Commons Math3?

- **Mathematics and Statistics**: Provides a comprehensive suite of functions for mathematical and statistical calculations.
- **Numerical Analysis**: Includes algorithms for optimization, integration, differentiation, and linear algebra.
- **Precision and Performance**: Ensures high-performance computation with precise numerical calculations.

### Example Usage

```java
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Math3Example {
    public static void main(String[] args) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        
        stats.addValue(1.2);
        stats.addValue(3.4);
        stats.addValue(5.6);
        
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();
        
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + std);
    }
}
```

## Conclusion

The Apache Commons libraries, specifically Lang3, Collections4, and Math3, significantly enhance Java development by providing utility methods and collection types not available in the standard JDK. They enable developers to write cleaner, more readable, and more efficient code by abstracting common programming tasks.
