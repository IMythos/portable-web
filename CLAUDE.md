# CodeViz Research Context

> **Note**: This file contains research context from CodeViz. Most recent contexts are at the bottom.

---

## Research Query

Funcionamiento general del backend desde control de roles, token, accesos por rol y endpoints desde repositorios haciendo uso de procedimientos almacenados

*Session: 139531eca80fc322235e35a7b5df1c89 | Generated: 7/4/2025, 12:10:12 AM*

### Analysis Summary

# Backend General Operation Report

This report details the general operation of the backend, focusing on role control, token management, role-based access, and endpoint interactions, including the use of stored procedures via repositories.

## High-Level Architecture: Authentication and Authorization Flow

The backend employs a Spring Security-based authentication and authorization mechanism, primarily utilizing JSON Web Tokens (JWTs) for stateless session management. The flow involves user authentication, JWT generation, and subsequent request authorization based on the token and assigned user roles.

### Core Components:

*   **Authentication Service ([AuthService](src/main/java/com/portable/app/auth/AuthService.java)):** Handles user login and authentication.
*   **Security Configuration ([SecurityConfig](src/main/java/com/portable/app/config/SecurityConfig.java)):** Defines security rules, authentication providers, and filters.
*   **JWT Services ([JwtService](src/main/java/com/portable/app/security/JwtService.java) & [JwtAuthorizationFilter](src/main/java/com/portable/app/security/JwtAuthorizationFilter.java)):** Responsible for JWT creation, validation, and processing incoming requests.
*   **User Details Service ([CustomUserDetailsService](src/main/java/com/portable/app/utils/CustomUserDetailsService.java)):** Loads user-specific data during the authentication process.
*   **Controllers ([controller](src/main/java/com/portable/app/controller)):** Expose RESTful API endpoints, some of which are secured by role-based access control.
*   **Repositories ([repository](src/main/java/com/portable/app/repository)):** Provide data access, potentially interacting with stored procedures.

## Detailed Flow: Authentication, Token Management, and Access Control

### 1. Authentication and Token Generation

The authentication process begins with a user sending login credentials to the [AuthController](src/main/java/com/portable/app/controller/AuthController.java).

*   **Login Endpoint:** The [AuthController](src/main/java/com/portable/app/controller/AuthController.java:24) exposes a `/auth/login` endpoint that accepts a [LoginRequestDto](src/main/java/com/portable/app/dto/LoginRequestDto.java) containing the username and password.
*   **Authentication:** The [AuthService](src/main/java/com/portable/app/auth/AuthService.java:20) uses Spring Security's `AuthenticationManager` to authenticate the user.
*   **JWT Creation:** Upon successful authentication, the [JwtService](src/main/java/com/portable/app/security/JwtService.java:24) generates a JWT. This token typically includes claims such as the username and user roles. The token is then returned to the client within a [LoginResponseDto](src/main/java/com/portable/app/dto/LoginResponseDto.java).

### 2. Token Validation and Authorization

Subsequent requests from the client include the generated JWT in the `Authorization` header.

*   **JWT Filter:** The [JwtAuthorizationFilter](src/main/java/com/portable/app/security/JwtAuthorizationFilter.java:26) intercepts incoming requests. It extracts the JWT from the header and validates its signature and expiration using the [JwtService](src/main/java/com/portable/app/security/JwtService.java).
*   **User Details Loading:** If the token is valid, the filter extracts the username and loads the user's details, including their authorities (roles), via the [CustomUserDetailsService](src/main/java/com/portable/app/utils/CustomUserDetailsService.java:20).
*   **Security Context:** The authenticated user's information is then set in Spring Security's `SecurityContextHolder`, making it available for subsequent authorization checks.

### 3. Role Control and Access by Role

Roles are defined as entities and are associated with users. Access to specific endpoints is controlled using Spring Security's `@PreAuthorize` annotation.

*   **Role Entity:** The [Role](src/main/java/com/portable/app/entity/Role.java) entity defines the available roles (e.g., "ADMIN", "USER").
*   **User-Role Relationship:** The [User](src/main/java/com/portable/app/entity/User.java) entity has a many-to-many relationship with the [Role](src/main/java/com/portable/app/entity/Role.java) entity, allowing a user to have multiple roles.
*   **Role-Based Access Control (RBAC):** Controllers use `@PreAuthorize` annotations to restrict access to methods based on the authenticated user's roles.
    *   Example: The [RoleController](src/main/java/com/portable/app/controller/RoleController.java:20) uses `@PreAuthorize("hasRole('ADMIN')")` to ensure only users with the "ADMIN" role can access its methods.
    *   Similarly, [UserController](src/main/java/com/portable/app/controller/UserController.java:20) and [EmployeeController](src/main/java/com/portable/app/controller/EmployeeController.java:20) also implement role-based access control.

### 4. Endpoints and Controller Structure

The `controller` package contains various controllers, each responsible for a specific domain.

*   **[AuthController](src/main/java/com/portable/app/controller/AuthController.java):** Handles user authentication and registration.
*   **[BranchController](src/main/java/com/portable/app/controller/BranchController.java):** Manages branch-related operations.
*   **[EmployeeController](src/main/java/com/portable/app/controller/EmployeeController.java):** Manages employee data.
*   **[ProductController](src/main/java/com/portable/app/controller/ProductController.java):** Handles product-related operations.
*   **[ProviderController](src/main/java/com/portable/app/controller/ProviderController.java):** Manages provider information.
*   **[PurchaseController](src/main/java/com/portable/app/controller/PurchaseController.java):** Deals with purchase transactions.
*   **[RoleController](src/main/java/com/portable/app/controller/RoleController.java):** Manages user roles.
*   **[UnitController](src/main/java/com/portable/app/controller/UnitController.java):** Manages unit-related data.
*   **[UserController](src/main/java/com/portable/app/controller/UserController.java):** Manages user accounts.
*   **[WarehouseController](src/main/java/com/portable/app/controller/WarehouseController.java):** Manages warehouse data.

Each controller typically interacts with a corresponding service layer (e.g., [BranchController](src/main/java/com/portable/app/controller/BranchController.java) with [BranchServiceImpl](src/main/java/com/portable/app/service/BranchServiceImpl.java)) to perform business logic and data persistence.

## Repositories and Stored Procedures

The `repository` package contains interfaces that extend Spring Data JPA's `JpaRepository`, providing standard CRUD operations. While the provided file structure indicates a strong reliance on Spring Data JPA for data access, direct usage of stored procedures via `@NamedStoredProcedureQuery` or `EntityManager.createStoredProcedureQuery()` is not immediately evident from the file names.

To confirm the use of stored procedures, a deeper inspection of the repository interfaces and their implementations would be required. If stored procedures were used, they would typically be defined within the entity classes using `@NamedStoredProcedureQuery` or called directly within custom repository implementations using `EntityManager`.

For example, if a stored procedure were used for a specific operation on the `Product` entity, it might be defined in [Product.java](src/main/java/com/portable/app/entity/Product.java) and then invoked from [ProductRepository.java](src/main/java/com/portable/app/repository/ProductRepository.java) or a custom implementation.

Without explicit code examples of stored procedure calls in the provided file structure, it's assumed that the primary data access mechanism is through Spring Data JPA's derived query methods or `@Query` annotations. If stored procedures were to be integrated, they would follow patterns similar to:

```java
// Example of a stored procedure definition in an Entity
@Entity
@NamedStoredProcedureQuery(name = "Product.calculateTotal", procedureName = "calculate_product_total", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Long.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "total", type = Double.class)
})
public class Product {
    // ...
}

// Example of calling a stored procedure from a Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Procedure(name = "Product.calculateTotal")
    Double calculateProductTotal(@Param("productId") Long productId);
}
```

---

## Research Query

Investigate the "@Procedure" component in detail. Provide a comprehensive analysis of its architecture, responsibilities, and implementation details.

*Session: f126611e2c2c6acf75e3d7f7a8149379 | Generated: 7/4/2025, 12:13:34 AM*

### Analysis Summary

# @Procedure Component Analysis

The `@Procedure` annotation in this codebase is a crucial element within the data access layer, specifically utilized by Spring Data JPA repositories to invoke stored procedures in the underlying database. It acts as a bridge, mapping Java method calls directly to predefined database procedures, thereby encapsulating complex SQL logic within the database and promoting a cleaner, more maintainable application layer.

## High-Level Architecture

The `@Procedure` annotation is found exclusively within the **repository interfaces** [repository interfaces](src/main/java/com/portable/app/repository), which form the data access layer of the application. These repositories are responsible for interacting with the database, and `@Procedure` facilitates the execution of stored procedures as part of this interaction. This design promotes a clear separation of concerns, where business logic resides in services and controllers, while data persistence and retrieval, including stored procedure execution, are handled by the repository layer.

## Responsibilities of @Procedure

The primary responsibilities of the `@Procedure` annotation are:

*   **Mapping Java Methods to Stored Procedures:** It explicitly links a method in a Spring Data JPA repository interface to a specific stored procedure in the database.
*   **Encapsulating Database Logic:** By allowing the invocation of stored procedures, it enables the application to leverage pre-optimized or complex database operations defined at the database level.
*   **Simplifying Data Access:** It abstracts away the boilerplate code typically required for calling stored procedures, allowing developers to interact with them as if they were regular Java methods.

## Implementation Details

The `@Procedure` annotation is applied directly to methods within the repository interfaces. Its core attribute is `procedureName`, which specifies the fully qualified name of the stored procedure to be executed in the database.

### Example Usage

Consider the `BranchRepository` [BranchRepository](src/main/java/com/portable/app/repository/BranchRepository.java) as an example:

```java
package com.portable.app.repository;

import com.portable.app.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Procedure(procedureName = "Inventario.usp_ListarSucursales")
    List<Branch> usp_ListarSucursales();

    @Procedure(procedureName = "Inventario.usp_BorrarSucursal")
    String usp_BorrarSucursal(@Param("idSucursal") int idSucursal);
}
```
[BranchRepository](src/main/java/com/portable/app/repository/BranchRepository.java:17)
[BranchRepository](src/main/java/com/portable/app/repository/BranchRepository.java:36)

In this example:

*   `usp_ListarSucursales()`: This method is mapped to the `Inventario.usp_ListarSucursales` stored procedure. When this Java method is called, the corresponding stored procedure will be executed, and its results will be mapped to a `List<Branch>`.
*   `usp_BorrarSucursal(@Param("idSucursal") int idSucursal)`: This method is mapped to the `Inventario.usp_BorrarSucursal` stored procedure. It takes an `idSucursal` parameter, which is passed to the stored procedure using the `@Param` annotation.

### Common Usage Patterns

The `@Procedure` annotation is consistently used across various repositories to perform common database operations via stored procedures, including:

*   **Listing Entities:** Methods like `usp_ListarEmpleadosRol` [EmployeeRepository](src/main/java/com/portable/app/repository/EmployeeRepository.java:18), `usp_ListarProductos` [ProductRepository](src/main/java/com/portable/app/repository/ProductRepository.java:17), `usp_ListarProveedores` [ProviderRepository](src/main/java/com/portable/app/repository/ProviderRepository.java:17), `usp_ListarCompras` [PurchaseRepository](src/main/java/com/portable/app/repository/PurchaseRepository.java:19), `usp_ListarRol` [RoleRepository](src/main/java/com/portable/app/repository/RoleRepository.java:17), `usp_ListarUnidades` [UnitRepository](src/main/java/com/portable/app/repository/UnitRepository.java:20), `usp_ListarUsuarios` [UserRepository](src/main/java/com/portable/app/repository/UserRepository.java:19), and `usp_ListarAlmacenes` [WarehouseRepository](src/main/java/com/portable/app/repository/WarehouseRepository.java:20) all retrieve lists of their respective entities.
*   **Deleting Entities:** Methods like `usp_BorrarSucursal` [BranchRepository](src/main/java/com/portable/app/repository/BranchRepository.java:36), `usp_BorrarEmpleado` [EmployeeRepository](src/main/java/com/portable/app/repository/EmployeeRepository.java:51), `usp_BorrarProducto` [ProductRepository](src/main/java/com/portable/app/repository/ProductRepository.java:48), `usp_BorrarProveedor` [ProviderRepository](src/main/java/com/portable/app/repository/ProviderRepository.java:36), `usp_BorrarCompra` [PurchaseRepository](src/main/java/com/portable/app/repository/PurchaseRepository.java:40), `usp_BorrarRol` [RoleRepository](src/main/java/com/portable/app/repository/RoleRepository.java:32), `usp_BorrarUnidad` [UnitRepository](src/main/java/com/portable/app/repository/UnitRepository.java:41), `usp_BorrarUsuario` [UserRepository](src/main/java/com/portable/app/repository/UserRepository.java:42), and `usp_BorrarAlmacen` [WarehouseRepository](src/main/java/com/portable/app/repository/WarehouseRepository.java:39) are used for deleting entities by invoking corresponding stored procedures.
*   **Specific Queries:** Some repositories also use `@Procedure` for more specific queries, such as `sp_Top7UnidadesPorMarca` [UnitRepository](src/main/java/com/portable/app/repository/UnitRepository.java:17) and `sp_ObtenerAlmacenes` [WarehouseRepository](src/main/java/com/portable/app/repository/WarehouseRepository.java:17).

## Architecture Integration

The `@Procedure` annotation is an integral part of the application's data access strategy. It allows the application to leverage the power of stored procedures for performance optimization, data integrity enforcement, and complex data manipulation directly within the database. This approach complements the use of standard JPA methods for simpler CRUD operations, providing a flexible and robust data access layer.

