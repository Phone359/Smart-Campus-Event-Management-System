# Risk and Technical Debt Log 

## Updated Risk Register 

| Risk ID | Technical Risk | Impact | Mitigation Strategy | Status | 

|---|---|---|---|---| 

| R1 | Authentication logic may allow incorrect access if role checking is incomplete | High | Apply Role-Based Access Control before allowing sensitive actions | Mitigated | 

| R2 | Event registration may allow duplicate registration records | Medium | Add duplicate checking before saving a registration | Mitigated | 

## Technical Debt Log 

| Debt ID | Shortcut Taken | Reason | Estimated Debt | Refactoring Plan | 

|---|---|---|---|---| 

| TD1 | Some validation messages are written directly inside controller classes | Faster prototype development | 2 hours | Move messages into a shared validation or message utility class | 

| TD2 | Database connection is first simulated before full database integration | Easier testing during construction | 3 hours | Replace simulation with real database configuration in the next cycle | 

| TD3 | Unit tests currently focus only on core logic | Limited construction time | 2 hours | Add more tests for notifications, feedback, and resource assignment | 

## Summary 

The team identified risks during implementation and applied mitigation strategies to reduce failure. The technical debt is acceptable for the current construction phase because it is documented, measured, and planned for future refactoring.