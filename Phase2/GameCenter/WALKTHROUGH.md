# Code Coverage

## Unit Test Coverage

Our unit test coverage is 23% of classes, 28% of lines. This includes the count for 
classes we were not expected to cover, and consequently is not exactly indicative of
our unit tests. Excluding adapters, controllers, and views, our coverage is 86% of classes
and 87% of lines.

## Most Important Classes

We would say that our most important classes are each games respective BoardManager class, as well as 
our GameActivities. BoardManagers since they contain most of the logic for our games, and GameActivities
since they run the games and allow the proper views to be displayed to the user.

## Design Patterns

We've used the following design patterns:
- Inheritance
- Model-View Controller
- Observable, Observer
- Serializable
- Iterable

In order we solve the following problems using these patterns:
- By using inheritance, we avoid duplicate code and try and modularize our classes to open them for 
extension, in accordance with SOLID principles.
- By using MVC: 
    - We allow people to work on seperate parts of a whole at the same time. 
    - Have high cohesion, since every class follows a logical grouping of assigned tasks.
    - Have low coupling, since each part of the MVC pattern is an isolated object with limited interaction.
    - Enable easy modification as virtue of our low coupling.
- By using Observer and Observable, we allow ourselves to have some action or event follow as a consequence of
another, which is essential to the nature of how our application must work.
- By using Seralizable, we enable ourselves to save essential data, such as who users are, and their respective
game states.
- By using Iterable, we allow ourselves to simplify our code in several places which need to iterate through some object.
This typically refers to our boards, which are represented as 2D arrays, allowing ourselves to avoid more complicated 
nested for loops.


## Scoreboard Design