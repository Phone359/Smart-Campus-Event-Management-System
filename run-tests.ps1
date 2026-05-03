$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$outDir = Join-Path $projectRoot "out"
$sourceFiles = Get-ChildItem -Path (Join-Path $projectRoot "src"), (Join-Path $projectRoot "tests") -Recurse -Filter "*.java" |
    Select-Object -ExpandProperty FullName

New-Item -ItemType Directory -Force -Path $outDir | Out-Null
javac -d $outDir $sourceFiles
java -cp $outDir AuthenticationTest
java -cp $outDir EventRegistrationTest
java -cp $outDir EventManagementTest
