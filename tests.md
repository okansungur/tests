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
