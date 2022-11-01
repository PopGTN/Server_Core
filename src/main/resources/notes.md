
## References & Notes 
These are something I learn that I want to remember. 

### Player variables

    // Create a map
    Map<UUID, YourType> map = new Map<UUID, YourType>();

    // Put values into it, using the player UUID as key and a value
    map.put(player.getUniqueId(), value);

    // Check if a value was put for the players UUID before
    map.has(player.getUniqueId());

    // Get a value with the players UUID (returns null if not set)
    YourType value = map.get(player.getUniqueId());
    
### If Statements when declaring Booleans
    boolean booleanName = (if statments that return false here);

### Ternary Operator
    variableType variableName = (boolean) ? "if true" : "If False";

### Switch Statment 

````
public class Main {
    public static void main(String[] args) {
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }
    }
}
````

### References
Some links that I want to remember and things I used

https://gist.github.com/aadnk/3928137

