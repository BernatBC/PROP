package classes;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Alexandre Ros Roger (alexandre.ros.roger@estudiantat.upc.edu)
 *                             (alexland7219@gmail.com) 
 **/
public class ConsultaSemblant {

    // There's only this query
    static ArrayList<Pair<Double, Document>> executeQuery(Llibreria lib, Document doc, int k){
        Set<Document> mySet = lib.getSetDocuments();

        // En ordre decreixent per el cosinus (els primers k seran els q es retornen)
        ArrayList<Pair<Double, Document>> orderedDocs = new ArrayList<>();

        if (!mySet.contains(doc)){
            System.out.println("Document is not in the library!");
            return (new ArrayList<Pair<Double, Document>>());
        }

        for (Document T : mySet){
            double cosinus = lib.computeCosinus(T, doc);

            boolean inserted = false;
            // Now insert document T!
            for (int i = 0; i < orderedDocs.size() && !inserted; ++i){
                if (orderedDocs.get(i).getL() >= cosinus) continue;
                if (orderedDocs.get(i).getL() < cosinus){
                    orderedDocs.add(i, new Pair<>(cosinus, T));
                }
            }

            if (!inserted) orderedDocs.add(new Pair<>(cosinus, T));
        }

        // Now we return the k first elements
        ArrayList<Pair<Double, Document>> returnSet = new ArrayList<>();

        for (int i = 0; i < k && i < orderedDocs.size(); ++i){
            returnSet.add(orderedDocs.get(i));
        }

        return returnSet;
    }

}