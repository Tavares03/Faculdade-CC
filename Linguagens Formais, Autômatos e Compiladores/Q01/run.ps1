param(
  [Parameter(ValueFromRemainingArguments = $true)]
  [string[]]$Args
)

$ErrorActionPreference = 'Stop'

$workspace = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $workspace

# Build first
& "$workspace\build.ps1"

if ($Args.Count -gt 0) {
  $inputLine = ($Args -join ' ')
  $inputLine | .\automato.exe
} else {
  Write-Host "Digite os lexemas (separados por espaço) e pressione Enter:" -ForegroundColor Yellow
  .\automato.exe
}
