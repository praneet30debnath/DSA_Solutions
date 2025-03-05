```java
import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoization {
    static Map<Integer, Integer> memo = new HashMap<>();

    public static int fib(int n) {
        if (memo.containsKey(n)) {
            System.out.println("Contains " + n);
            return memo.get(n); // Return cached result
        } else {
            System.out.println("Doesn't contains" + n);
        }
        if (n <= 1) {
            System.out.println("N value " + n);
            return n; // Base case
        }
        
        int result = fib(n - 1) + fib(n - 2); // Recursive call
        memo.put(n, result);
        System.out.println(memo);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Output: 55
    }
}
```

### Output
```
Doesn't contains10
Doesn't contains9
Doesn't contains8
Doesn't contains7
Doesn't contains6
Doesn't contains5
Doesn't contains4
Doesn't contains3
Doesn't contains2
Doesn't contains1
N value 1
Doesn't contains0
N value 0
{2=1}
Doesn't contains1
N value 1
{2=1, 3=2}
Contains 2
{2=1, 3=2, 4=3}
Contains 3
{2=1, 3=2, 4=3, 5=5}
Contains 4
{2=1, 3=2, 4=3, 5=5, 6=8}
Contains 5
{2=1, 3=2, 4=3, 5=5, 6=8, 7=13}
Contains 6
{2=1, 3=2, 4=3, 5=5, 6=8, 7=13, 8=21}
Contains 7
{2=1, 3=2, 4=3, 5=5, 6=8, 7=13, 8=21, 9=34}
Contains 8
{2=1, 3=2, 4=3, 5=5, 6=8, 7=13, 8=21, 9=34, 10=55}
55

=== Code Execution Successful ===
```

