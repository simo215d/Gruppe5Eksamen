package model.persistence.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import model.datastrukture.Behandler;
import model.datastrukture.Forloeb;
import model.datastrukture.Patient;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseDAO {
    private Firestore db;
    public void opretForloeb(Behandler behandler, Patient patient) throws BehandlerManglerException, PatientManglerException, IOException, PatientErAlleredeIForloebException {
        // Her fanges mulige fejl.
        if (patient == null) throw new PatientManglerException(){};
        if (behandler == null) throw new BehandlerManglerException(){};
        if (patient.getErIForloeb()) throw new PatientErAlleredeIForloebException(){};
        // Hvis ikke vi har en connection til databasen så bliver den oprettet her.
        if (this.db == null) {
            this.db = hentDatabase();
        }
        // Sådan opretter man et forløb til firestore.
        Forloeb forloeb = new Forloeb(behandler, patient);
        CollectionReference cr = db.collection("Forloeb");
        DocumentReference document = cr.document(behandler.getNavn() + patient.getNavn());
        document.set(forloeb);
        // Patientens status om at være i et forløb sættet til at være sand og ændring uploades til databasen.
        patient.setErIForloeb(true);
        CollectionReference crp = db.collection("Patienter");
        DocumentReference patientDocument = crp.document(patient.getNavn());
        patientDocument.set(patient);
    }

    public ArrayList<Patient> hentPatienter(boolean b) throws ExecutionException, InterruptedException {
        // Her laver vi et Arraylist og bruger databasen til at finde alle patienter som enten er, eller ikke er i et forløb.
        ArrayList<Patient> patienter = new ArrayList<>();
        CollectionReference cr = db.collection("Patienter");
        Query query = cr.whereEqualTo("erIForloeb",b);
        ApiFuture<QuerySnapshot> futureQuery = query.get();
        List<DocumentSnapshot> documents = futureQuery.get().getDocuments();
        // Her tilføjer vi patienterne til arraylisten en efter en, efter at vi har tjekket om de er i et forløb eller ej.
        for (DocumentSnapshot next : documents) {
            patienter.add(next.toObject(Patient.class));
        }
        return patienter;
    }

    private Firestore hentDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("./fir-demo-bfec6-firebase-adminsdk-rmxrg-8ef1361333.json");
        FirebaseOptions options = new FirebaseOptions.Builder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();
        return db;
    }
}
