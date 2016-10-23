package main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import entities.Bucket;
import entities.Stack;
import entities.StackElement;
import process.BucketCreator;
import process.StackCreator;
import process.StoreStack;

public class Main {

    public static void main(String[] args) {
		List<Bucket> buckets = BucketCreator.GetAllBucketInFolder("./nautilus/nautilus-training/");
		StoreStack storeStack = new StoreStack(buckets);
		
		try {
			File folder = new File("./nautilus/nautilus-testing/");
			String[] files = folder.list(new FilenameFilter() {
			    @Override
			    public boolean accept(File current, String name) {
				return new File(current, name).isFile();
			    }
			});
			
			for (String file : files) {
				Stack stack = StackCreator.getStackFromFilePath("./nautilus/nautilus-testing/" + file); // res = 1337
			    Bucket b = storeStack.getBestBucket(stack);
			    
				System.out.println(file.substring(0, file.length()-4) + "  -> " + b.getId());
			}
			
			//Actuellement :
			// On parse les fichier et on construit le tout de cette manière.
			// Un Bucket contient une liste de Stack
			// Un Stack contient une liste de StackElement
			// Un StackElement contient une adresse, une méthode, des arguments, un path et des variables
			//
			// On prend un Stack a classer et on le compare avec chacun des stacks de chacun des bucket.
			// On récupère le pourcentage de similarité avec le Stack le plus ressemblant d'un bucket
			// On réalise cette étape avec chacun des buckets et le range avec le stack qui lui ressemble le plus.
			//
			// Afin de comparer deux Stacks, on compare leurs StackElement entre eux.
			// Pour se faire on sélectionne le StackElement du Stack à comparer avec le plus d'élèments comparable que possible.
			// Avec l'index, on regarde s'il en existe un du même index dans le Stack d'en face
			//	Si oui, on les compare entre eux.
			//	Si non, on séléctionne le StackElement du Stack du bucket de la même façon que le StackElement du Stack a comparer.
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
