# Report 3 Defense Evidence

## Verification Scope
- Authentication supports happy, negative, and boundary cases.
- Registration protects capacity, duplicate registration, and RBAC rules.
- Event management validates organizer/admin event creation and rejects student creation.
- Audit logging records security-relevant login, registration, and event-management outcomes.

## SQA Metrics
| Module | Main Decisions | Estimated V(G) | FO Risk |
| --- | ---: | ---: | --- |
| AuthenticationService | 3 | 4 | Low |
| RegistrationService | 4 | 5 | Medium |
| EventManagementService | 3 | 4 | Low |
| AuthorizationService | 2 | 3 | Low |

V(G) is estimated as decision points + 1. FO risk is based on how many user-facing failure outcomes can occur in the module.

## Acceptance Test Summary
| Case Type | Evidence |
| --- | --- |
| Happy path | Valid admin login, student registration, organizer event creation |
| Negative | Wrong password, duplicate registration, unauthorized role actions |
| Boundary | Empty username/password, full event capacity |

## Git / PR Audit Talking Points
- Feature-branch workflow is documented in the README.
- FTR log records reviewer, moderator, developer, recorder, issues found, and final decision.
- Test log records expected and actual results for each acceptance case.

## Technical Debt Log
| Debt Item | Risk | Planned Stewardship |
| --- | --- | --- |
| In-memory repositories | Data resets after program exit | Replace with database repository in deployment phase |
| Plain password comparison | Not acceptable for production security | Add salted password hashing before real release |
| Console-based controllers | Limited UI integration | Connect controllers to web or desktop UI layer |
| Manual main-method tests | Less formal than JUnit | Convert to JUnit in next iteration |

## AI Oversight Statement
AI support was used only for drafting, checking consistency, and improving code structure. The team remains accountable for validating requirements, reviewing security choices, and explaining every implemented module during defense.
