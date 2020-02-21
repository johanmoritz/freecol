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

1. What are your results for ten complex functions?
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

## Coverage

### Tools
<!-- 
Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment? -->

We used OpenClover to get code coverage for the project. This was a fairly straightforward installation and setup proccess but it relied on quite a bit of search-engine-luck to get everything running smoothly. OpenClover has a library (as a `.jar` file) made directly for the ant build system as well as a "two-liner" setup for any ant project. It took us some time to figure out where to put the library on our system and also how where in the build.xml file to put this "two-liner". Thankfully the FreeCol project is setup in a really nice, modular way so as soon as we found the correct placement of the setup, all test commands could be used together with OpenClover and a bunch of OpenClover commands showed up as tasks when listing them with `ant`.

### DYI

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

1. How detailed is your coverage measurement?

2. What are the limitations of your own tool?

3. Are the results of your tool consistent with existing coverage tools?

### Coverage improvement

Show the comments that describe the requirements for the coverage.

[Report of old coverage](https://github.com/johanmoritz/freecol/blob/assignment-documentation/FreeCol_Before/index.html)

[Report of new coverage](https://github.com/johanmoritz/freecol/blob/assignment-documentation/FreeCol_After/index.html)

**Test cases added:**

All test cases have been documented in the following issues:

BaseCostDecided::getCost [Commit c0f](https://github.com/johanmoritz/freecol/commit/c0fcbb68d50f69fcc8e39832dd6123fc50786144), [Commit 624](https://github.com/johanmoritz/freecol/commit/6248844cda338e19f31b48370848db09cb2c9fac)

Map::findMapPath [Issue #39](https://github.com/johanmoritz/freecol/issues/39), see this commit: [51b965d](https://github.com/johanmoritz/freecol/commit/51b965d0406b63c0974bbd811d31a335890c5f7d)

Monarch::actionIsValid [Issue #40](https://github.com/johanmoritz/freecol/issues/40)

ModelMessage::getDefaultDisplay [Issue #42](https://github.com/johanmoritz/freecol/issues/42), see this commit: [5c54519](https://github.com/johanmoritz/freecol/commit/5c54519465ba9c5ca3e2cddf34f747baeeab9eed)

Scope::equals [Issue #48](https://github.com/johanmoritz/freecol/issues/48)

## Refactoring

Plan for refactoring complex code:

Estimated impact of refactoring (lower CC, but other drawbacks?).

Carried out refactoring (optional)

git diff ...

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
