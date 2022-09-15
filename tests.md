### Clean Code

__TDD__: Is a methodology focuses on iterative development lifecycle. Add a test (Red) new test fails.Write enough code to pass the test(Green). Run test and refactor. So that in the end you save time.

__BDD__: (Cucumber) Gherkin Language is used. (Given When Then) BDD is concerned about result of higher level scenarios. TDD is focused on testing smaller pieces of functionality in isolation.TDD you should have the knowledge of programming language whereas in BDD you dont have to.

 __3-Laws of TDD__ :
 
Rule - 1 : You may not write any production code without a failing unit test first 

Rule - 2 : Write only enough test code as is sufficient enough to fail (not compiling=failing) usage of undeclared class 

Rule - 3 : Only write a minimal code that makes the failing test pass. declare the class only


Test code is as important as production code. Make it readable. Clean tests folllow 5 other rules as FIRST.
- Fast(runs quickly) 
- Independent(should not depend on each other)
- Repeatable(All environments) 
- Self-Validating(boolean output fail or pass)  
- Timely(before production code) 
- 

## Testing Strategies

Active Record is a good choice for domain logic that isn't too complex, such as creates, reads, updates, and deletes.The active record pattern is an approach to accessing data in a database. A database table or view is wrapped into a class. Thus, an object instance is tied to a single row in the table. After creation of an object, a new row is added to the table upon save.Active Record facilitates the creation and use of business objects whose data requires persistent storage to a database. Example: ORM tools

A Transaction Script organizes all this logic primarily as a single procedure, making calls directly to the database or through a thin database wrapper. Each transaction will have its own Transaction Script, although common subtasks can be broken into subprocedures


<p align="center">
  <img  src="https://github.com/okansungur/tests/blob/main/tests/testing_pyramids.png"><br/>
  Testing Pyramids
</p>




<p align="center">
  <img  src="https://github.com/okansungur/tests/blob/main/tests/testingstr_desiciontree.png"><br/>
  Testing Strategy Desicion Tree
</p>
