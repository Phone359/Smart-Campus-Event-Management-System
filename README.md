# Smart Campus Event Management System

## Overview
This system is designed to manage campus events in a centralized and efficient way. It allows students, faculty, and administrators to interact with events through a secure and user-friendly platform.

## Features
- User authentication with RBAC
- Event creation and management
- Event registration with capacity control
- Notification system
- Audit logging for accountability

## Technology
- Java
- GitHub (Version Control)
- Layered Architecture (N-tier)

## Current Prototype Scope
The submitted prototype demonstrates the core defense logic for authentication, authorization, event creation, registration capacity control, duplicate registration prevention, and audit logging. Data is stored in memory for classroom demonstration.

## How to Run Tests
From the project root:

```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem src,tests -Recurse -Filter *.java).FullName
java -cp out AuthenticationTest
java -cp out EventRegistrationTest
java -cp out EventManagementTest
```

The tests cover happy path, negative, boundary, and RBAC cases for Report 3 evidence.

Important: run Java commands from the project root folder, not from `src\app`. Because the code uses packages, compiling only `Main.java` from inside `src\app` will not find `controller`, `model`, `service`, or `repository` classes.

For an easier option on Windows PowerShell:

```powershell
.\run-demo.ps1
.\run-tests.ps1
```

## Team Members
- Ricardo Paul (Project Manager)
- Praweechai Thararuenroeng (System Architect)
- Phone Pyae Kyaw (Lead Developer)
- Poe Ei Ei Khaing (QA)
- Phyoe Kyaw Zin (Business Analyst)

## Branching Strategy
We use a Feature Branch workflow. Each feature is developed in its own branch and merged through Pull Requests after review.
