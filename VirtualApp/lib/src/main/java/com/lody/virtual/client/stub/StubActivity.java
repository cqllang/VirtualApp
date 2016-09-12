package com.lody.virtual.client.stub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lody.virtual.BuildConfig;
import com.lody.virtual.client.core.PatchManager;
import com.lody.virtual.client.hook.patchs.am.HCallbackHook;
import com.lody.virtual.helper.proto.StubActivityRecord;

/**
 * @author Lody
 *
 */
public abstract class StubActivity extends Activity {

	private static final boolean DEBUG = BuildConfig.DEBUG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent stubIntent = getIntent();
		try {
			PatchManager.getInstance().checkEnv(HCallbackHook.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// Note:
		// ClassLoader of savedInstanceState is not exist now.
		super.onCreate(null);
		StubActivityRecord r = new StubActivityRecord(stubIntent);
		if (r.intent == null) {
			if (DEBUG) {
				Toast.makeText(this, "Ops...", Toast.LENGTH_SHORT).show();
			}
		} else {
			Intent intent = r.intent;
			startActivity(intent);
		}
		finish();
	}



	public static class C0 extends StubActivity {
	}

	public static class C1 extends StubActivity {
	}

	public static class C2 extends StubActivity {
	}

	public static class C3 extends StubActivity {
	}

	public static class C4 extends StubActivity {
	}

	public static class C5 extends StubActivity {
	}

	public static class C6 extends StubActivity {
	}

	public static class C7 extends StubActivity {
	}

	public static class C8 extends StubActivity {
	}

	public static class C9 extends StubActivity {
	}

	public static class C10 extends StubActivity {
	}

	public static class C11 extends StubActivity {
	}

	public static class C12 extends StubActivity {
	}

	public static class C13 extends StubActivity {
	}

	public static class C14 extends StubActivity {
	}

	public static class C15 extends StubActivity {
	}

	public static class C16 extends StubActivity {
	}

	public static class C17 extends StubActivity {
	}

	public static class C18 extends StubActivity {
	}

	public static class C19 extends StubActivity {
	}

	public static class C20 extends StubActivity {
	}

	public static class C21 extends StubActivity {
	}

	public static class C22 extends StubActivity {
	}

	public static class C23 extends StubActivity {
	}

	public static class C24 extends StubActivity {
	}

	public static class C25 extends StubActivity {
	}

	public static class C26 extends StubActivity {
	}

	public static class C27 extends StubActivity {
	}

	public static class C28 extends StubActivity {
	}

	public static class C29 extends StubActivity {
	}

	public static class C30 extends StubActivity {
	}

	public static class C31 extends StubActivity {
	}

	public static class C32 extends StubActivity {
	}

	public static class C33 extends StubActivity {
	}

	public static class C34 extends StubActivity {
	}

	public static class C35 extends StubActivity {
	}

	public static class C36 extends StubActivity {
	}

	public static class C37 extends StubActivity {
	}

	public static class C38 extends StubActivity {
	}

	public static class C39 extends StubActivity {
	}

	public static class C40 extends StubActivity {
	}

	public static class C41 extends StubActivity {
	}

	public static class C42 extends StubActivity {
	}

	public static class C43 extends StubActivity {
	}

	public static class C44 extends StubActivity {
	}

	public static class C45 extends StubActivity {
	}

	public static class C46 extends StubActivity {
	}

	public static class C47 extends StubActivity {
	}

	public static class C48 extends StubActivity {
	}

	public static class C49 extends StubActivity {
	}

	public static class C50 extends StubActivity {
	}

	public static class C51 extends StubActivity {
	}

	public static class C52 extends StubActivity {
	}

	public static class C53 extends StubActivity {
	}

	public static class C54 extends StubActivity {
	}

	public static class C55 extends StubActivity {
	}

	public static class C56 extends StubActivity {
	}

	public static class C57 extends StubActivity {
	}

	public static class C58 extends StubActivity {
	}

	public static class C59 extends StubActivity {
	}

	public static class C60 extends StubActivity {
	}

	public static class C61 extends StubActivity {
	}

	public static class C62 extends StubActivity {
	}

	public static class C63 extends StubActivity {
	}

	public static class C64 extends StubActivity {
	}

	public static class C65 extends StubActivity {
	}

	public static class C66 extends StubActivity {
	}

	public static class C67 extends StubActivity {
	}

	public static class C68 extends StubActivity {
	}

	public static class C69 extends StubActivity {
	}

	public static class C70 extends StubActivity {
	}

	public static class C71 extends StubActivity {
	}

	public static class C72 extends StubActivity {
	}

	public static class C73 extends StubActivity {
	}

	public static class C74 extends StubActivity {
	}

	public static class C75 extends StubActivity {
	}

	public static class C76 extends StubActivity {
	}

	public static class C77 extends StubActivity {
	}

	public static class C78 extends StubActivity {
	}

	public static class C79 extends StubActivity {
	}

	public static class C80 extends StubActivity {
	}

	public static class C81 extends StubActivity {
	}

	public static class C82 extends StubActivity {
	}

	public static class C83 extends StubActivity {
	}

	public static class C84 extends StubActivity {
	}

	public static class C85 extends StubActivity {
	}

	public static class C86 extends StubActivity {
	}

	public static class C87 extends StubActivity {
	}

	public static class C88 extends StubActivity {
	}

	public static class C89 extends StubActivity {
	}

	public static class C90 extends StubActivity {
	}

	public static class C91 extends StubActivity {
	}

	public static class C92 extends StubActivity {
	}

	public static class C93 extends StubActivity {
	}

	public static class C94 extends StubActivity {
	}

	public static class C95 extends StubActivity {
	}

	public static class C96 extends StubActivity {
	}

	public static class C97 extends StubActivity {
	}

	public static class C98 extends StubActivity {
	}

	public static class C99 extends StubActivity {
	}



}
