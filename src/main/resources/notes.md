
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

### Ternary Operator
    variableType variableName = (boolean) ? "if true" : "If False";

### References
Some links that I want to remember and things I used

https://gist.github.com/aadnk/3928137

