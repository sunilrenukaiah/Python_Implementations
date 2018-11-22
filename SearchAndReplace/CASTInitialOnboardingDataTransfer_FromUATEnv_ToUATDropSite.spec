# -*- mode: python -*-

block_cipher = None


a = Analysis(['D:\\Office\\Tools\\PNCTools\\UC1\\CASTInitialOnboardingDataTransfer_FromUATEnv_ToUATDropSite.py'],
             pathex=['D:\\Office\\Tools\\SearchAndReplace'],
             binaries=[],
             datas=[],
             hiddenimports=[],
             hookspath=[],
             runtime_hooks=[],
             excludes=[],
             win_no_prefer_redirects=False,
             win_private_assemblies=False,
             cipher=block_cipher)
pyz = PYZ(a.pure, a.zipped_data,
             cipher=block_cipher)
exe = EXE(pyz,
          a.scripts,
          a.binaries,
          a.zipfiles,
          a.datas,
          name='CASTInitialOnboardingDataTransfer_FromUATEnv_ToUATDropSite',
          debug=False,
          strip=False,
          upx=True,
          runtime_tmpdir=None,
          console=True )
