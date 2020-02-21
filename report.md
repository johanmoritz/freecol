# Report for assignment 3

Group 18

## Project

Name: FreeCol

URL: https://github.com/johanmoritz/freecol

An open-source turn-based strategy game based on Colonization. The goal of the game is to explore and colonize America. The game is written in Java, built with the `ant` build system and has been worked on continually by a group of contributers since 2002. It's currently on its `0.11.6` version.

## Onboarding experience

The project is very easy to build. All you need to do to build it is running `ant` and it will do the rest of the work for you. 

To install `ant`, we used (sdkman)[https://sdkman.io/] which is a package manager for java systems. sdkman can be installed with a one-liner and the installation gives post-install instructions as well, making the process very simple. With the help of sdkman, ant can be installed with a one-liner as well. Some people in the group had issues with their terminals not reloading automatically, but after restarting them all worked well.

Regarding the documentation quality of the build tools, it's a bit hard to tell how good they are. The usage we needed from sdkman as well as from ant could be easily found from their respective cli help, so, as far as we can tell, they are very well documented. We have not had to dig deep into any documentation though so this just might be pure luck.

As far as we can tell, the only secondary components the build installed were unit testing framework and other libraries used by the project. Nothing is installed globally.

The build always worked perfectly, without any errors and running tests was also very easy on our systems (macOS and ubuntu), only needing to run for example`ant alltest`.

In conclusion, Yes, we plan to continue using this project.

## Complexity
**Function: Monarch::actionIsValid**

CCN according to lizard: 19

Purpose: Checks if a specified action is valid for a Monarch at the current game time.

Complexity analysis: I believe that this function does not do too much or too little. It only has a switch statement in it that covers every possible value the parameter can have (considering it's an enum). If there would be something to change, then it would be to remove the empty cases which could reduce the CCN slightly and use the default case instead (which right now will never be used).

**Function:**  TerrainGenerator::createLandRegions

Purpose: Takes a map of a continuous stretch of land, and divides it into a number of regions depending on how the map is defined.

Complexity analysis: createLandRegions is complex because it tries to heavily optimize a lot of different processes by running them together and avoiding unnecessary data copying between functions. The function itself is inherently complex because it does a lot of different steps. A way to reduce the complexity would needlessly sacrifice a bit of speed for a smaller a more succinct function.

**Function:**  Player::readChild

CCN according to lizard: 22

Purpose: Reads a player object, based of a tag, from an xml file and then creates it.

Complexity analysis: This function is basically a bunch of branches that go through as many (if not all?) cases as possible of what the tag in the xml file could be. These will be difficult to reduce the complexity of, but there are inner if-statements that could be moved out (to perhaps the create function called) as they do a little bit more than just read the object and create it. It also handles cases such as nulls, which could be moved out.

**Function:**  Scope::equals

CCN according to lizard: 18

Purpose: Compares two scope objects to each other

Complexity analysis: Compares the relevant fields to each other to check that these two objects are in fact equal. However, also does some null checks which could be handled in the get-ers for said fields. This would remove that logic from the function completely, which makes sense considering that it should only compare the values and not have to handle nulls.

**Function:**  BaseCostDecider::getCost

CNN according to lizard:: 24

CNN according to manuel count: 19

Purpose::Determines the cost of a single move.

Complexity analysis:
The function takes four arguments: (final Unit unit, final Location oldLocation, final Location newLocation, int movesLeftBefore)

-Unit: the unit making the move
-oldLocation: the location we are moving from
-newLocation: the location we are moving to
-movesLeftBefore: The moves left before making the move
-Return: the cost of moving a unit from oldLocation to newLocation

The function compares different moves based on what unit type, terrain, new location and old location, determines the cost for the move. The code is easy to follow and together with the documentation quite self evident.

The reason for the high CCN for the function is mainly the many switch statements and if statements, it is hard to decrease the CCN without moving out some of the methods.
This function was refactored in #57.

**Function:** ModelMessage::getDefaultDisplay

CCN according to lizard: 22

Purpose: Returns the display object of a given message type.

Complexity analysis: The function takes two parameters messageType and source, messageType is of course the given message type and source is what the display will be taken from. The function is basically a big switch statement and most of the cases are fall through. Some cases even fall through to the default case which increases the complexity redundantly.

**Function:**  Monarch::initializeCaches

CCN according to lizard: 25

CNN according to manuel count: 19 and 22

Purpose: Cache the unit types and roles for support and mercenary offers.

Complexity analysis: Structurize unit in a neat way based on their abilities and group them together. Depending on the type of unit based Navel, Bombard, Land, Mercenary, the units get put into a group of corresponding class.
The code is not very well documented, but it is quite clear what it does.

The function consist of if-else statement and therefore hard to improve the CCN without moving some of the methods to outside the function.

**Function:**  Map::findMapPath

CCN according to lizard: 23

Purpose: Finds a path on the map for a unit, from start tile to end tile.

Complexity analysis: The function takes the parameters unit, start, end, carrier, costDecider, and lb (LogBuilder). It has several if statements. The main one checks whether the start and end tile are contiguous (belong to the same part of the map). The other if statements checks attributes of the unit and the tiles, such as:

-water/land units
-water/land tiles
-carrier units
-unit is on carrier

It makes sense that the function is complex since pathfinding is a complex task. The function is not overly complex since its sole purpose is to set the parameters of the main pathfinding function called searchMap, which is much more complex.
Documentation is clear.

**Function:**  Unit::setLocation

CCN according to lizard: 20

CCN calculated manually: 15

Purpose: Sets the location for a Unit instance.

Complexity analysis: This is a setter method in the Unit class for the location field. It only takes the parameter newLocation, which is the value the field should be set to. In the game, units belong to colonies depending on which colony their location belongs to. Other attributes of the unit also depends on which colony they are in. All this is handled by this method, which cause the high complexity. We have not attempted to refactor this function but an idea which could reduce complexity is to move this colony logic to the Colony class. There is, however, a comment stating the following:
```java
// We have to handle this issue here in setLocation as this is
// the only place that contains information about both
// locations.
```

So there seems to have been some thought in handling the colony logic here.
Documentation is clear.


**Function:** Player::writeChildren

CCN according to lizard: 22

Purpose: Writes Player attributes (children) to a XML file using the FreeColXMLWriter class.

Complexity analysis: The function converts each field in the Player class to XML and writes to a stream (FreeColXMLWriter). The complexity is high since it contains a bunch of if statements that check whether certain fields exists, and loops for fields that are Collections. We have not refactored this but it would probably be a good idea to move much of this logic to the FreeColXMLWriter class. For example, adding a writeField method (check if field != null and then writes it), and a writeCollection method (writes a collection/list of values to the stream). In its current state the method is unnecessarily complex.
Documentation is clear in the FreeColXMLWriter class, the method in the Player class lacks documentation.

## Coverage

### Tools
<!-- 
Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment? -->

We used OpenClover to get code coverage for the project. This was a fairly straightforward installation and setup proccess but it relied on quite a bit of search-engine-luck to get everything running smoothly. OpenClover has a library (as a `.jar` file) made directly for the ant build system as well as a "two-liner" setup for any ant project. It took us some time to figure out where to put the library on our system and also how where in the build.xml file to put this "two-liner". Thankfully the FreeCol project is setup in a really nice, modular way so as soon as we found the correct placement of the setup, all test commands could be used together with OpenClover and a bunch of OpenClover commands showed up as tasks when listing them with `ant`.

### DIY
The code coverage tool we created keeps track of visited branches using a HashMap with one HashMap per branch:
```java
public static HashMap<String, HashMap<Integer, Integer>> functions = new HashMap<>();
```
A function's HashMap is stored using the function name as key, for example "initializeCaches" for the initializeCaches() function.

CodeCoverage.run(<function_name>) must be added manually to a branch for the tool to measure its coverage.

This method fetches the line number from which it is called and uses this as key in the function's HashMap to keep track of how many times the branch is visited:
```java
public static void run(String functionName) {
    int lineNumber = getLineNumber();
    HashMap<Integer, Integer> functionEntries = CodeCoverage.functions.getOrDefault(functionName, new HashMap<>());
    int touches = functionEntries.getOrDefault(lineNumber, 1);
    functionEntries.put(lineNumber, touches + 1);
    CodeCoverage.functions.put(functionName, functionEntries);
}
```
See the full code for the tool in this branch:

[link](https://github.com/johanmoritz/freecol/tree/feature/code-coverage-system)

Or use this git command:
```
git diff master feature/code-coverage-system
```
### Evaluation

1. How detailed is your coverage measurement?
Our coverage tool implements branch coverage and not statement coverage.
It can be used for statement coverage if the code is refactored when manually adding the calls to CodeCoverage.run().

2. What are the limitations of your own tool?
It requires manual instrumentation.
Fall trough cases in switch statements needs to be refactored and have breaks added.

3. Are the results of your tool consistent with existing coverage tools?
The results of our tool differs slightly from the OpenClover results.
OpenClover measures both branch and statement cover, which could explain why the results differ.
Also, OpenClover's automatic branch and statement detection may miss some branches and statements.

Here are some coverage measurements according to OpenClover and our own tool (after coverage improvements):

Function | OpenClover | mobergliuslefors code coverage tool
-- | -- | --
initializeCaches | 97,5% | 95.7%
writeChildren | 49,2% | 50.0%
readChild | 50,4% | 32.1%
setLocation | 93,3% | 92.6%
getDefaultDisplay | 52,7% | 50.0%
updateTileImprovementPlans | 75,9% | 76.0%

### Coverage improvement

Show the comments that describe the requirements for the coverage.

[Report of old coverage](https://github.com/johanmoritz/freecol/blob/assignment-documentation/FreeCol_Before/index.html)

[Report of new coverage](https://github.com/johanmoritz/freecol/blob/assignment-documentation/FreeCol_After/index.html)

**Test cases added:**

All test cases have been documented in the following issues:

BaseCostDecided::getCost [Issue #38](https://github.com/johanmoritz/freecol/issues/38), see this commit: [c0fcbb6](https://github.com/johanmoritz/freecol/commit/c0fcbb68d50f69fcc8e39832dd6123fc50786144) & [6248844](https://github.com/johanmoritz/freecol/commit/6248844cda338e19f31b48370848db09cb2c9fac)

Map::findMapPath [Issue #39](https://github.com/johanmoritz/freecol/issues/39), see this commit: [51b965d](https://github.com/johanmoritz/freecol/commit/51b965d0406b63c0974bbd811d31a335890c5f7d)

Monarch::actionIsValid [Issue #40](https://github.com/johanmoritz/freecol/issues/40), see these commits: [bd0b6f3](https://github.com/johanmoritz/freecol/commit/bd0b6f3d4c0731e333844f97e88445c81407c8d6) & [ebd3db1](https://github.com/johanmoritz/freecol/commit/ebd3db16a59a713f179ba0c9347717454ecea6c8)

ModelMessage::getDefaultDisplay [Issue #42](https://github.com/johanmoritz/freecol/issues/42), see this commit: [5c54519](https://github.com/johanmoritz/freecol/commit/5c54519465ba9c5ca3e2cddf34f747baeeab9eed)

Scope::equals [Issue #48](https://github.com/johanmoritz/freecol/issues/48), see this commit: [f520e74](https://github.com/johanmoritz/freecol/commit/f520e74eb58d41d40badb741544e7d4e4a3e5f9b)

## Refactoring

**Refactoring ModelMessage::getDefaultDisplay:**

This function has a switch statement with a lot of "fall through" cases, i.e. cases without breaks. There's as many as 8 of these that fall through to the default which is a very interesting design choice.

So the plan is to reduce the complexity by just deleting all of those cases and letting them default to default instead.

The refactoring basically removes 8 cases so the complexity should be decremented by 8.

This refactoring wasn't carried out.


Plan for refactoring complex code:

Estimated impact of refactoring (lower CC, but other drawbacks?).

Carried out refactoring (optional)

git diff ...

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
