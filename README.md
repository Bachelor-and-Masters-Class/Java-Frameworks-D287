RepairPro Hardware - Inventory Management Customization

Scenario

This project is a customized inventory management system for RepairPro Hardware, a fictional small business that sells repair toolkits and individual tools for home and professional use. The business model supports bundling tools into specialized kits for electricians, plumbers, or general DIY customers. The application was customized to suit the specific needs of RepairPro Hardware by modifying a Java Spring Boot application with Thymeleaf templates.

The customizations include inventory validation, enhanced user messaging, bug fixes, form validations, and testing. These changes improve the system’s usability, reliability, and user experience.



TASK INSTRUCTIONS & TRACKED CHANGES (C-J)

Each note below describes what was changed, where it was changed (file name and line number), and why it was changed.

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

File Name: MainScreen.html

Line 23: Added my shop name

```<h1>RepairPro Hardware</h1>```

File Name: MainScreen.html

Line 36-40: Added my product names

```<li>Cordless Drill</li>```  
```<li>Electrician Tool Kit</li>``` 
```<li>Plumbing Repair Kit</li>```  
```<li>General Repair Set</li>```  
```<li>Deluxe Home Repair Kit</li>``` 

File Name: MainScreen.html

Lines 64-68: Added my part names
```<li>Cordless Drill</li>```  
```<li>Wire Stripper</li>```  
```<li>Pipe Wrench</li>``` 
```<li>Utility Knife</li>```  
```<li>Adjustable Spanner</li>``` 



D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

File Name: MainScreen.html

Line 26: Added a button linked to the “About Us” page below the shop name.

```<a href="/templates/about.html">About Us</a>```

File Name: About.html

Line 16-17: Added a statement describing my company.

```<p>RepairPro Hardware is your trusted source for high-quality repair kits and tools.``` 
```We provide essential equipment for electricians, plumbers, and everyday homeowners.</p>```

File Name: About.html

Line 11: Added a button navigating back to the mainscreen/home page.

```<a href="mainscreen.html">Home</a>```

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

File Name: BootStrapData.java

Line 38-106: Added five parts in my sample inventory

```InhousePart ip1 = new InhousePart();```
```ip1.setId(201);```
```ip1.setName("Hammer");```
```ip1.setPrice(12.99);```
```ip1.setInv(15);```
```ip1.setMinInv(5);```
```ip1.setMaxInv(50);```
```inhousePartRepository.save(ip1);```
```InhousePart thePart = null;```
```inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();```
```for (InhousePart part : inhouseParts) {```
```if (part.getName().equals("Hammer")) thePart = part;```
```}```
```InhousePart ip2 = new InhousePart();```
```ip2.setId(202);```
```ip2.setName("Wrench");```
```ip2.setPrice(9.49);```
```ip2.setInv(20);```
```ip2.setMinInv(5);```
```ip2.setMaxInv(50);```
```inhousePartRepository.save(ip2);```
```thePart = null;```
```inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();```
```for (InhousePart part : inhouseParts) {```
```if (part.getName().equals("Wrench")) thePart = part;```
```}```
```InhousePart ip3 = new InhousePart();```
```ip3.setId(203);```
```ip3.setName("Pliers");```
```ip3.setPrice(6.75);```
```ip3.setInv(18);```
```ip3.setMinInv(3);```
```ip3.setMaxInv(60);```
```inhousePartRepository.save(ip3);```
```thePart = null;```
```inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();```
```for (InhousePart part : inhouseParts) {```
```if (part.getName().equals("Pliers")) thePart = part;```
```}```
```InhousePart ip4 = new InhousePart();```
```ip4.setId(204);```
```ip4.setName("Screwdriver");```
```ip4.setPrice(5.99);```
```ip4.setInv(30);```
```ip4.setMinInv(5);```
```ip4.setMaxInv(75);```
```inhousePartRepository.save(ip4);```
```thePart = null;```
```inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();```
```for (InhousePart part : inhouseParts) {```
```if (part.getName().equals("Screwdriver")) thePart = part;```
```}```
```InhousePart ip5 = new InhousePart();```
```ip5.setId(205);```
```ip5.setName("Drill Bit Set");```
```ip5.setPrice(14.99);```
```ip5.setInv(10);```
```ip5.setMinInv(2);```
```ip5.setMaxInv(40);```
```inhousePartRepository.save(ip5);```
```thePart = null;```
```inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();```
```for (InhousePart part : inhouseParts) {```
```if (part.getName().equals("Drill Bit Set")) thePart = part;```
```}```

File Name: BootStrapData.java
Lines 153-157: Added products in my sample inventory

```Product repairKit = new Product("Home Repair Kit", 39.99, 5);```
```Product plumbingKit = new Product("Plumbing Kit", 29.99, 4);```
```Product drillKit = new Product("Drill Kit", 49.99, 3);```
```Product electricianKit = new Product("Electrician Starter Kit", 44.99, 2);```
```Product multipackTools = new Product("Multi-Pack Tool Set", 59.99, 3);```



F. Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

File Name: MainScreen.html

Lines 39-41: Added a buy now button next to the update and delete buttons

```<form th:action="@{'/products/buy/' + ${product.id}}" method="post">```  
```<button type="submit">Buy Now</button>```
```</form>```
```<form th:action="@{'/products/update/' + ${product.id}}" method="get">```
```<button type="submit">Update</button>```
```</form>```
```<form th:action="@{'/products/delete/' + ${product.id}}" method="post">```
```<button type="submit">Delete</button>```


File Name: MainScreenController.java

Lines 44-45: Added code so the “Buy Now” button decrement the inventory of a product by one

```if (product.getInv() > 0) {```
```product.setInv(product.getInv() - 1);```


File Name: MainScreenController.java

Line 46-53: Message added that indicates the success of a product, if the product is out of stock or product not found.

```productService.save(product);```
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

Lines 26-30: Added additional fields to the part entity (max/min inventory)

```private int inv;```
```private int minInv;```
```private int maxInv;```

File Name: BootStrapData.java

Lines 110-114: Modified my sample inventory to include the max and min fields

```Product repairKit = new Product("Home Repair Kit", 39.99, 5, 1, 10);```
```Product plumbingKit = new Product("Plumbing Kit", 29.99, 4, 1, 8);```
```Product drillKit = new Product("Drill Kit", 49.99, 3, 1, 6);```
```Product electricianKit = new Product("Electrician Starter Kit", 44.99, 2, 1, 5);```
```Product multipackTools = new Product("Multi-Pack Tool Set", 59.99, 3, 1, 7);```

File Name: InhousePartForm.html

Lines 34-42: Added inputs for min/max inventory

```<p><label>```
```<input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>```
```<p><label>```
```<input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Max Inventory Error</p>``` 


File Name: OutsourcedPartForm.html

Lines 34-41: Added inputs for min/max inventory

```<input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Min Inventory Error</p>```
```<p><label>```
```<input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4" />```
```</label></p>```
```<p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Max Inventory Error</p>``` 


File Name: Application.properities

Line 7: Renamed the file the persistent storage is saved to

```#spring.datasource.url=jdbc:h2:file:./data/repairpro_inventory_db```


File Name: Part.java

Lines 98-99: Modified code to ensure that the inventory is between or at the minimum and maximum value.

```public boolean isInventoryValid() {```
```return inv >= minInv && inv <= maxInv;```

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

File Name: AddProductController.java

Lines 62-63: Error message added for low inventory when adding and updating products lowers the part inventory below the minimum.

```model.addAttribute("error", "Adding this product will reduce inventory for part '" + p.getName() + "' below its minimum (" + p.getMin() + ").");```
```updatePartLists(model, product);```


File Name: AddPartController.java

Lines 75-76: Added error messages when adding and updating parts if the inventory is greater than the maximum.

```}else if (part.getInv() > part.getMaxInv()) {```
```bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be greater than the maximum.");```


File Name: AddPartController.java

Lines 73-74: Added error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.

```if (!part.isInventoryValid()) {```
```bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");```


I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

File Name: PartTest.java

Lines 163-185: Added unit tests for the maximum and minimum fields to the PartTest class.

```@Test```
```void testInventoryBelowMinimumIsInvalid() {```
```partIn.setMin(5);```
```partIn.setMax(15);```
```partIn.setInv(3);```
```assertFalse(partIn.isInventoryValid(), "Inventory below min should be invalid");```
```}```
```@Test```
```void testInventoryAboveMaximumIsInvalid() {```
```partIn.setMin(1);```
```partIn.setMax(10);```
```partIn.setInv(12);```
```assertFalse(partIn.isInventoryValid(), "Inventory above max should be invalid");```
```}```
```@Test```
```void testInventoryWithinRangeIsValid() {```
```partIn.setMin(3);```
```partIn.setMax(10);```
```partIn.setInv(7);```
```assertTrue(partIn.isInventoryValid(), "Inventory within range should be valid");```
```}```


J.  Remove the class files for any unused validators in order to clean your code.

Deleted unused validators:
File name:DeletePartValidator. Java
File name: ValidDeletePart.java 
