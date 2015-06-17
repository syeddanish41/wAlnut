package model.MARK_II;

import model.unimplementedBiology.CNS;
import model.unimplementedBiology.LGN;
import model.unimplementedBiology.PNS;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 5, 2013
 */
public class NervousSystem {
    private model.unimplementedBiology.CNS CNS;
    private model.unimplementedBiology.PNS PNS;

    public NervousSystem(Neocortex neocortex, LGN lgn, Retina retina) {
        this.CNS = new CNS(neocortex, lgn);
        this.PNS = new PNS(retina);
    }

    public CNS getCNS() {
        return this.CNS;
    }

    public PNS getPNS() {
        return this.PNS;
    }
}
