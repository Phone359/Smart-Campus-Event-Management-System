# Formal Technical Review Issue Log 

## FTR Session Information 

| Item | Detail | 

|---|---| 

| Review Type | Formal Technical Review | 

| Project | Smart Campus Event Management System | 

| Review Focus | Authentication, registration, error handling, and documentation | 

| Review Date | 2026-04-27 | 

| Moderator | Ricardo Paul | 

| Reviewer | Poe Ei Ei Khaing | 

| Developer | Phone Pyae Kyaw | 

| Recorder | Phyoe Kyaw Zin | 

## Defects Found 

| Issue ID | Module | Defect Description | Severity | Action Taken | Status | 

|---|---|---|---|---|---| 

| FTR-01 | AuthenticationController | Empty username and password were not checked before login validation | Medium | Added input validation before authentication logic | Closed | 

| FTR-02 | RegistrationService | Duplicate registration was not prevented in the first version | High | Added duplicate registration check | Closed | 

| FTR-03 | DatabaseConnection | Database failure message was too technical for users | Medium | Replaced technical message with humanized error message | In Progress | 

| FTR-04 | Documentation | Risk log did not include technical debt estimate | Low | Added estimated debt hours and refactoring plan | Closed | 

## Final Consensus Decision 

Decision: Rework, then Accept 

The team agreed that the system was mostly stable, but some issues needed correction before final acceptance. After the authentication and registration issues were corrected, the reviewed modules were accepted for continued testing and integration.