import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseHelper {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private fun initializeFirebase() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://servicomp-44f11-default-rtdb.asia-southeast1.firebasedatabase.app/")
    }

    fun getAuthInstance(): FirebaseAuth {
        if (!::auth.isInitialized) {
            initializeFirebase()
        }
        return auth
    }

    fun getDatabaseInstance(): FirebaseDatabase {
        if (!::database.isInitialized) {
            initializeFirebase()
        }
        return database
    }

    fun getCurrentUserId(): String? {
        val user = getAuthInstance().currentUser
        return user?.uid
    }

    fun getUserDataReference(userId: String?): DatabaseReference {
        return getDatabaseInstance().reference.child("users").child(userId ?: "")
    }

    fun getUserDataReference(): DatabaseReference {
        val userId = getCurrentUserId()
        return getUserDataReference(userId)
    }
}
