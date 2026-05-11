import AddModule.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server {

    public static void main(String args[]) {

        try {

            ORB orb = ORB.init(args, null);

            POA rootpoa =
                    POAHelper.narrow(
                            orb.resolve_initial_references(
                                    "RootPOA"));

            rootpoa.the_POAManager().activate();

            AddImpl addImpl = new AddImpl();

            org.omg.CORBA.Object ref =
                    rootpoa.servant_to_reference(
                            addImpl);

            Add href =
                    AddHelper.narrow(ref);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references(
                            "NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(
                            objRef);

            String name = "ADD";

            ncRef.rebind(
                    ncRef.to_name(name),
                    href);

            System.out.println(
                    "CORBA Server Ready...");

            orb.run();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}