1. How to run the code
----------------------
To build the solution run in command file:
gradlew build

To build the solution run in command file:
gradlew run

The program will process file 'in.csv' from the project root directory and output a 'out.csv' file in the same directory.

Alternatively you can import the project into your IDE as a gradle project and run tomczak.mini.wrangler.ExampleUsage
class to achieve the same result.


2. Architectural overview
-------------------------
This wrangler will transform input csv file into output csv file applying transformations to the data.
There is a in-language DSL that is designed to specify the transformations.

For example:

Rules(
	Column("OrderID").from("Order Number").transformWith(IntegerParser()),
	Column("OrderDate").from("Year","Month","Day").transformWith(Concatenator("/", DateTimeParser("yyyy/M/d", "yyyy-MM-dd HH:mm"))),
	Column("ProductID").from("Product Number").transformWith(StringParser()),
	Column("ProductName").from("Product Name").transformWith(ProperCase()),
	Column("Quantity").from("Count").transformWith(BigDecimalParser("en-US")),
	Column("Unit").transformWith(PlainFiller("kg"))
)

will:
- Rename 'Order Number' column to 'OrderID' and parse as Integer
- Add a new column, concatenating 'Year', 'Month' and 'Day' resulting in 'OrderDate' column and parse it as
  DateTime
- Rename 'Product Number' to 'ProductId' and parse as String
- Rename column 'ProductName' to 'Product Name', parse it as a String and Proper case the data
- Rename column 'Count' to 'Quantity' and parse as BigDecimal
- Add a new column 'Unit' with fixed value "kg"
- Keep only above 6 columns from the input file

The rule engine is implemented in package tomczak.mini.wrangler.rule.engine.

If this code was designed to be used as a library, then the class to be used for CSV processing is tomczak.mini.wrangler.CSVWrangler

3. Next steps
-------------
This solution is designed to be open for extensions according to open-closed principle.
So when different transformation rules will appear in the future, one need to implement 'Transformer' interface with the new transformation rule.

It will also be good to store erroneous rows in the separate file along with the comment what was invalid instead of just logging it.

