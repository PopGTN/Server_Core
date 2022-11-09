
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
        Default;
              System.out.println("Sunday");
        }
    }
}
````
Switch with strings
````
    public String getDayDescription() {
        String output = "";

        switch (description.toLowerCase()) {

            //Add any temperature conditions that apply to rainy
            case "rainy":
                if (temperature >= 5) {
                    output += "it is rainy";
                }
                if (temperature < 5) {
                    output += "it is a terrible day";
                }
                break;
            //Add any temperature conditions that apply to sunny
            case "sunny":
                if (temperature > 15) {
                    output += "it is a nice day";
                }
                // * - "it is an OK day" if it is not "rainy" and the weather is between 5 and 15 degrees. 
                if ((temperature >= 5) && (temperature <= 15)) {                    
                        output += "it is an OK day";
                }
                if (temperature > 25) {
                    output += "it is a great day";
                }
                break;
            // * - "it is a nice day" if the temperature is over 15 degrees and it is not "rainy". 
            //Add any temperature conditions that apply to cloudy
            case "cloudy":
                if (temperature > 15) {
                    output += "it is a nice day";
                }
                // * - "it is an OK day" if it is not "rainy" and the weather is between 5 and 15 degrees. 
                if ((temperature >= 5) && (temperature <= 15)) {                    
                        output += "it is an OK day";
                }
                break;
        }
        return output;
    }
````
## Get a Random Number
````
    public int getRandom(int max, int min) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
````
### You assign values in if statments parmintors
````
if ( var1 <= var2)
//Var var 1 will = VAR 2
````
### References
Some links that I want to remember and things I used

https://gist.github.com/aadnk/3928137

