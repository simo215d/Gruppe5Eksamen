package persistence.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import model.datastrukture.*;
import model.exceptions.BehandlerManglerException;
import model.exceptions.PatientErAlleredeIForloebException;
import model.exceptions.PatientManglerException;
import persistence.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseDAOImpl implements DAO {
    private Firestore db;
    @Override
    public void opretForloeb(Behandler behandler, Patient patient) throws BehandlerManglerException,
            PatientManglerException, IOException, PatientErAlleredeIForloebException,
            ExecutionException, InterruptedException {
        // Her fanges mulige fejl.
        if (patient == null) throw new PatientManglerException(){};
        if (behandler == null) throw new BehandlerManglerException(){};
        if (patient.getErIForloeb()) throw new PatientErAlleredeIForloebException(){};
        // Hvis ikke vi har en connection til databasen så bliver den oprettet her.
        if (this.db == null) {
            this.db = hentDatabase();
        }
        // Sådan opretter man et forløb til firestore.
        Forloeb forloeb = newForloeb(behandler,patient);
        CollectionReference cr = db.collection("Forloeb");
        DocumentReference document = cr.document(behandler.getEmail() + patient.getEmail());
        document.set(forloeb).get();
        // Patientens status om at være i et forløb sættet til at være sand og ændring uploades til databasen.
        patient.setErIForloeb(true);
        CollectionReference crp = db.collection("Patienter");
        DocumentReference patientDocument = crp.document(patient.getEmail());
        patientDocument.set(patient);
    }

    @Override
    public Forloeb newForloeb(Behandler behandler, Patient patient) {
        return new ForloebImpl(behandler, patient);
    }

    @Override
    public ArrayList<Patient> hentPatienter(boolean erIForloeb) throws ExecutionException, InterruptedException, IOException {
        if (this.db == null) {
            this.db = hentDatabase();
        }
        // Her bruger vi databasen til at finde alle patienter som enten er, eller ikke er i et forløb baseret på parametret.
        CollectionReference cr = db.collection("Patienter");
        Query query = cr.whereEqualTo("erIForloeb",erIForloeb);
        ApiFuture<QuerySnapshot> futureQuery = query.get();
        List<DocumentSnapshot> documents = futureQuery.get().getDocuments();
        // Her laver vi en arraylist og tilføjer patienterne til arraylisten en efter en.
        ArrayList<Patient> patienter = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            patienter.add(document.toObject(PatientImpl.class));
        }
        return patienter;
    }

    @Override
    public ArrayList<Behandler> hentBehandlere() throws ExecutionException, InterruptedException, IOException {
        if (this.db == null) {
            this.db = hentDatabase();
        }
        // Her bruger vi databasen til at finde alle patienter som enten er, eller ikke er i et forløb baseret på parametret.
        CollectionReference cr = db.collection("Behandlere");
        ApiFuture<QuerySnapshot> futureQuery = cr.get();
        List<DocumentSnapshot> documents = futureQuery.get().getDocuments();
        // Her laver vi en arraylist og tilføjer patienterne til arraylisten en efter en.
        ArrayList<Behandler> behandlere = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            behandlere.add(document.toObject(BehandlerImpl.class));
        }
        return behandlere;
    }

    @Override
    public ArrayList<Patient> hentForloeb(Behandler behandler) throws IOException, ExecutionException, InterruptedException {
        if (this.db == null) {
            this.db = hentDatabase();
        }
        CollectionReference cr = db.collection("Forloeb");
        Query query = cr.whereEqualTo("behandler", behandler);
        ApiFuture<QuerySnapshot> futureQuery = query.get();
        List<DocumentSnapshot> documents = futureQuery.get().getDocuments();
        ArrayList<Patient> patienter = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            patienter.add(document.toObject(PatientImpl.class));
        }
        return patienter;
    }

    @Override
    public Forloeb hentForloeb(Patient patient) throws IOException, ExecutionException, InterruptedException {
        if (this.db == null) {
            this.db = hentDatabase();
        }
        CollectionReference cr = db.collection("Forloeb");
        Query query = cr.whereEqualTo("patientEmail", patient.getEmail());
        ApiFuture<QuerySnapshot> futureQuery = query.get();
        List<DocumentSnapshot> documents = futureQuery.get().getDocuments();
        ArrayList<Forloeb> forloeb = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            forloeb.add(castDocumentSnapshotToForloeb(document));
        }
        return forloeb.get(0);
    }

    public Forloeb castDocumentSnapshotToForloeb(DocumentSnapshot documentSnapshot){
        return documentSnapshot.toObject(ForloebImpl.class);
    }

    public Firestore hentDatabase() throws IOException {
        InputStream serviceAccount = getClass().getResourceAsStream("/persistence/firebase/ServiceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if (FirebaseApp.getApps().size() == 0) {
            FirebaseApp.initializeApp(options);
        }
        Firestore db = FirestoreClient.getFirestore();
        return db;
    }
}
