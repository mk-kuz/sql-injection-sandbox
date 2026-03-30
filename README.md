SQL Injection & Security Lab (Spring Boot)
This is a hands-on security laboratory built with Spring Boot 3.5, H2 In-Memory Database, and Thymeleaf.
It demonstrates exactly how SQL Injection vulnerabilities are created through string concatenation and how they can be completely neutralized using parameterized queries.
Features:
- Live Vulnerable Search: A product search tool that allows for UNION-based data exfiltration.
- Bypassable Login: A login screen that can be breached using tautology attacks (e.g., ' OR 1=1 --).
- Administrative Control: Demonstration of session management and role-based access.
- Destructive Payloads: Support for multi-statement injections like '; DROP TABLE ....
- H2 Debug Console: Integrated web interface to monitor the database state in real-time.
