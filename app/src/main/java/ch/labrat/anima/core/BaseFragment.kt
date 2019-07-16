package ch.labrat.anima.core

import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import android.content.Context
import androidx.core.content.ContextCompat.checkSelfPermission
import ch.labrat.anima.R
import ch.labrat.anima.utilities.MY_PERMISSIONS_REQUEST_CALENDAR

abstract class BaseFragment : Fragment() {

    /**
     * Returns true if the context has access to any given permissions.
     */
    fun arePermissionsEnabled(permissions: Array<String>): Boolean {
        return permissions.none { checkSelfPermission( activity as Context, it ) != PackageManager.PERMISSION_GRANTED};
    }


    fun requestMultiplePermissions(permissions: Array<String>, requestCode: Int) {
        val remainingPermissions = permissions.filter { checkSelfPermission( activity as Context, it) != PackageManager.PERMISSION_GRANTED }
        requestPermissions(remainingPermissions.toTypedArray(), requestCode)
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        // Todo: Commented code is old and to be removed. But only when the code after this comment is working
/*
        if (requestCode == 101) {
            if(grantResults.any { it != PackageManager.PERMISSION_GRANTED }){
                if(permissions.any { shouldShowRequestPermissionRationale(it) }){
                    AlertDialog.Builder(activity as Context)
                        .setMessage("Your error message here")
                        .setPositiveButton(getString(R.string.allow)) { dialog, which -> requestMultiplePermissions(permissions) }
                        .setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog.dismiss() }
                        .create()
                        .show()
                }
            }
            //all is good, continue flow
        }
*/
        var errorMessage = "Something went wrong while requesting permission"
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CALENDAR -> errorMessage = getString(R.string.permission_for_calendar_text)
            else -> return
        }

        if(grantResults.any { it != PackageManager.PERMISSION_GRANTED }){
            if(permissions.any { shouldShowRequestPermissionRationale(it) }){
                AlertDialog.Builder(activity as Context)
                    .setMessage(errorMessage)
                    .setPositiveButton(getString(R.string.allow)) { dialog, which -> requestMultiplePermissions(permissions, requestCode) }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog.dismiss() }
                    .create()
                    .show()
            }
        }
    }
}