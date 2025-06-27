RepairPro Hardware - Inventory Management Customization

Scenario

This project is a customized inventory management system for RepairPro Hardware, a fictional small business that sells repair toolkits and individual tools for home and professional use. The business model supports bundling tools into specialized kits for electricians, plumbers, or general DIY customers. The application was customized to suit the specific needs of RepairPro Hardware by modifying a Java Spring Boot application with Thymeleaf templates.

The customizations include inventory validation, enhanced user messaging, bug fixes, form validations, and testing. These changes improve the system’s usability, reliability, and user experience.



TASK INSTRUCTIONS & TRACKED CHANGES (C-J)

Each note below describes what was changed, where it was changed (file name and line number), and why it was changed.

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

File Name: MainScreen.html

Line 23: Added a <header> section that includes the shop name.

```<h1>RepairPro Hardware</h1>```

File Name: MainScreen.html

Line 36-37 : Created a #products section that loops through productList and displays each product’s name, price, inventory, and ID.

```<div class="product-entry" th:each="product : ${productList}">```
```<p><strong>ID:</strong> <span th:text="${product.id}">0</span></p>```

File Name: MainScreen.html

Lines 45 and 69: Inside each product, displayed associated part names using th:each="part : ${product.parts}".
Added a #parts section to display all available parts from partList using another th:each.

```<li th:each="part : ${product.parts}" th:text="${part.name}"></li>```  
and  
```<li th:each="part : ${partList}" th:text="${part.name}"></li>``` 

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

File Name: MainScreen.html

Line 26: Added a button linked to the “About Us” page below the shop name.

``` <a href="/about">About Us</a>```

File Name: About.html

Line 16-17: Added a statement describing my company.

```<p>RepairPro Hardware is your trusted source for high-quality repair kits and tools.``` 
```We provide essential equipment for electricians, plumbers, and everyday homeowners.</p>```

File Name: About.html

Line 11: Added a navigation link to return to the main screen.

```<a href="mainscreen">Home</a>```

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

File Name: BootStrapData.java

Line 36-40: Added five parts in my sample inventory

```InhousePart drill = new InhousePart("Cordless Drill", 49.99, 50, 5, 60);```
```InhousePart stripper = new InhousePart("Wire Stripper", 12.50, 40, 4, 50);```
```InhousePart wrench = new InhousePart("Pipe Wrench", 24.75, 30, 3, 40);```
```InhousePart knife = new InhousePart("Utility Knife", 8.75, 30, 5, 50);```
```InhousePart spanner = new InhousePart("Adjustable Spanner", 14.00, 25, 5, 40);```

File Name: BootStrapData.java

Lines 47-51: Added products in my sample inventory

```Product repairKit = new Product("Deluxe Home Repair Kit", 99.99, 5);```
```Product electricianKit = new Product("Electrician Tool Set", 89.50, 3);```
```Product plumbingKit = new Product("Plumbing Repair Kit", 74.25, 4);```
```Product drillKit = new Product("Cordless Drill Kit", 79.99, 6);```
```Product generalRepair = new Product("General Repair Set", 82.00, 2);```

F. Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

File Name: MainScreen.html

Lines 50-60: Added a buy now button next to the update and delete buttons

                ```<form th:action="@{'/buy/' + ${product.id}}" method="post">```
                    ```<button type="submit">Buy Now</button>```
                ```</form>```
                ```<form th:action="@{/showProductFormForUpdate}" method="get">```
                   ```<input type="hidden" name="productID" th:value="${product.id}" />```
                    ```<button type="submit">Update</button>```
                ```</form>```
                ```<form th:action="@{'/products/delete/' + ${product.id}}" method="post">```
                    ```<button type="submit">Delete</button>```


File Name: MainScreenController.java

Lines 54-55: Added code so the “Buy Now” button decrement the inventory of a product by one

```if (product.getInv() > 0) {```
```product.setInv(product.getInv() - 1);```


File Name: MainScreenController.java

Line 57-63: Message added that indicates the success of a product, if the product is out of stock or product not found.

```redirectAttributes.addFlashAttribute("message", "Purchase successful!");```
```} else {```
```redirectAttributes.addFlashAttribute("message", "Out of stock.");```
```}```
```} else {```
```redirectAttributes.addFlashAttribute("message", "Product not found.");```
```}```


G. Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

File Name: Part.java

Lines 26-33: Added additional fields to the part entity (max/min inventory)

```@Min(value = 0, message = "Inventory must be non-negative")```
```private int inv;```
```@Min(value = 0, message = "Minimum inventory must be non-negative")```
```private int minInv;```
```@Min(value = 1, message = "Maximum inventory must be at least 1")```
```private int maxInv```

File Name: BootStrapData.java

Lines 36-40: Modified my sample inventory to include the max and min fields

```InhousePart drill = new InhousePart("Cordless Drill", 49.99, 50, 5, 60);```
```InhousePart stripper = new InhousePart("Wire Stripper", 12.50, 40, 4, 50);```
```InhousePart wrench = new InhousePart("Pipe Wrench", 24.75, 30, 3, 40);```
```InhousePart knife = new InhousePart("Utility Knife", 8.75, 30, 5, 50);```
```InhousePart spanner = new InhousePart("Adjustable Spanner", 14.00, 25, 5, 40);```

File Name: InhousePartForm.html

Lines 30-37: Added inputs for min/max inventory

```<input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>```
```<p><label>```
```<input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Max Inventory Error</p>``` 


File Name: OutsourcedPartForm.html

Lines 33-40: Added inputs for min/max inventory

```<input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>```
```<p><label>```
```<input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Max Inventory Error</p>``` 


File Name: Application.properties

Line 4: Renamed the file the persistent storage is saved to

```spring.datasource.url=jdbc:h2:file:./data/repairpro_inventory_db```


File Name: Part.java

Lines 69-70: Modified code to ensure that the inventory is between or at the minimum and maximum value.

```public boolean isInventoryValid() {```
```return inv >= minInv && inv <= maxInv;```

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

File Name: AddProductController.java

Lines 57-58: Error message added for low inventory when adding and updating products lowers the part inventory below the minimum.

```model.addAttribute("error", "Adding this product will reduce inventory for part '" +```
```part.getName() + "' below its minimum of " + part.getMinInv() + ".");```

File Name: AddPartController.java

Lines 99-100: Added error messages when adding and updating parts if the inventory is greater than the maximum.

```}else if (part.getInv() > part.getMaxInv()) {```
```bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be greater than the maximum.");```

File Name: AddPartController.java

Lines 97-98: Added error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.

```if (!part.isInventoryValid()) {```
```bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");```


I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

File Name: PartTest.java

Lines 166-195: Added unit tests for the maximum and minimum fields to the PartTest class.

```@Test```
```void testInventoryBelowMinimumIsInvalid() {```
```partIn.setMin(5);```
```partIn.setMax(15);```
```partIn.setInv(3);```
```assertFalse(partIn.isInventoryValid(), "Inventory below min should be invalid");```
```}```
    ```@Test```
    ```void testInventoryAboveMaximumIsInvalid() {```
       ``` partIn.setMin(1);```
        ```partIn.setMax(10);```
        ```partIn.setInv(12);```
        ```assertFalse(partIn.isInventoryValid(), "Inventory above max should be invalid");```
    ```}```
    ```@Test```
    ```void testMinimumInventoryIsSetCorrectly() {```
        ```partIn.setMin(4);```
        ```partIn.setMax(15);```
        ```partIn.setInv(3);```
        ```assertFalse(partIn.isInventoryValid(), "Inventory should be invalid when below the new min");```
    ```}```
    ```@Test```
    ```void testMaximumInventoryIsSetCorrectly() {```
        ```partIn.setMin(2);```
        ```partIn.setMax(6);```
        ```partIn.setInv(7);```
        ```assertFalse(partIn.isInventoryValid(), "Inventory should be invalid when above the new max");```

J.  Remove the class files for any unused validators in order to clean your code.

Deleted unused validators:
File name:DeletePartValidator. Java
File name: ValidDeletePart.java 
