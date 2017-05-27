package trees;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Each FileHierarchy object describes a hierarchical collection of documents
 * and folders, in which a folder may contain any number of documents and other
 * folders. Within a given folder, all documents and folders have different
 * names. This file hierarchy is represented by a tree, f ileTree, whose
 * elements are Descriptor objects.
 * 
 * @author ANKIT DAIM
 * 
 */
public class FileHierarchy {

	private Tree<Descriptor> fileTree;
	public final String DELIMITER = "/";
	public final String ROOTPATHNAME = "";

	// Construct a new, empty file hierarchy.
	public FileHierarchy() {
		fileTree = new LinkedUnOrderedTree<>();
	}

	public void create(String localName, Tree.Node<Descriptor> folder) {
		// Create a new document, named localName, inside folder in this
		// file hierarchy, or throw an exception if this the name is already
		// used.
		if (used(localName, folder))
			throw new IllegalArgumentException(localName + " already used");
		Descriptor descr = new Descriptor(localName, false,
				Descriptor.DEFAULT_PERMISSIONS, new File(""));
		fileTree.addChild(folder, descr);
	}

	public List<String> find(String targetName) {
		// Return a list of the complete path-names of all documents named
		// targetName in this file hierarchy.
		List<String> pathnames = new LinkedList<>();
		findAll(targetName, fileTree.root(), pathnames);
		return pathnames;
	}

	private String pathName(Tree.Node<Descriptor> node) {
		// Return the full pathname of the file at node in this file hierarchy.
		if (node == fileTree.root())
			return ROOTPATHNAME;
		else {
			Descriptor descr = (Descriptor) node.getElement();
			return pathName(fileTree.parent(node)) + DELIMITER + descr.name;
		}
	}

	private void findAll(String targetName, Tree.Node<Descriptor> folder,
			List<String> pathnames) {
		// Add to pathnames the complete pathnames of all documents named
		// targetName in folder, and all of its sub-folders, in this file
		// hierarchy.
		Iterator<Descriptor> children = fileTree.children(folder);
		while (children.hasNext()) {
			@SuppressWarnings("unchecked")
			Tree.Node<Descriptor> child = (Tree.Node<Descriptor>) children
					.next();
			Descriptor childDescr = (Descriptor) child.getElement();
			if (childDescr.isFolder)
				findAll(targetName, child, pathnames);
			else if (targetName.equals(childDescr.name))
				pathnames.add(pathName(child));
		}
	}

	private boolean used(String name, Tree.Node<Descriptor> folder) {
		// Return true if and only if name is the name of a document or folder
		// within folder in this file hierarchy.
		Iterator<Descriptor> children = fileTree.children(folder);
		while (children.hasNext()) {
			@SuppressWarnings("unchecked")
			Tree.Node<Descriptor> child = (Tree.Node<Descriptor>) children
					.next();
			Descriptor childDescr = (Descriptor) child.getElement();
			if (name.equals(childDescr.name))
				return true;
		}
		return false;
	}

	/**
	 * Each Descriptor object describes a document or folder. This description
	 * contains the document or folder's local name (name); a flag indicating
	 * whether it is a folder (isFolder); a flag whose least significant bits
	 * indicate whether it is readable, writable, and executable (permissions);
	 * and a reference to its underlying file in the case of a document (file).
	 * 
	 * @author ANKIT DAIM
	 * 
	 */
	private static class Descriptor implements Comparable<Descriptor>{
		private String name;
		private boolean isFolder;
		@SuppressWarnings("unused")
		private byte permissions;
		@SuppressWarnings("unused")
		private File file; // null for a folder

		private static final byte DEFAULT_PERMISSIONS = 0x07;

		/**
		 * @param name
		 * @param isFolder
		 * @param permissions
		 * @param file
		 */
		public Descriptor(String name, boolean isFolder, byte permissions,
				File file) {
			super();
			this.name = name;
			this.isFolder = isFolder;
			this.permissions = permissions;
			this.file = file;
		}

		@Override
		public int compareTo(Descriptor o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
