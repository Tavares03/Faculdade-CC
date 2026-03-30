$ErrorActionPreference = 'Stop'

$workspace = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $workspace

$vswhere = 'C:\Program Files (x86)\Microsoft Visual Studio\Installer\vswhere.exe'
if (!(Test-Path $vswhere)) {
  throw "vswhere não encontrado em: $vswhere. Instale o Visual Studio Build Tools 2022."
}

$installPath = & $vswhere -latest -products * -requires Microsoft.VisualStudio.Component.VC.Tools.x86.x64 -property installationPath
if (![string]::IsNullOrWhiteSpace($installPath) -eq $false) {
  throw "Visual Studio Build Tools com MSVC não encontrado (component VC.Tools.x86.x64)."
}

$devShellDll = Join-Path $installPath 'Common7\Tools\Microsoft.VisualStudio.DevShell.dll'
if (!(Test-Path $devShellDll)) {
  throw "DevShell DLL não encontrado em: $devShellDll"
}

Import-Module $devShellDll
Enter-VsDevShell -VsInstallPath $installPath -SkipAutomaticLocation -DevCmdArguments '-arch=x64 -host_arch=x64' | Out-Null

Write-Host "Compilando com MSVC (cl) ..." -ForegroundColor Cyan
cl /nologo /W4 /TC main.c /Fe:automato.exe

Write-Host "OK: gerado automato.exe" -ForegroundColor Green
