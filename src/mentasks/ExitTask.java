package mentasks;
/**
 *
 */
public class ExitTask implements Task {
    /**
     *
     */
    private boolean exit;

    /**
     * constructor.
     */
    public ExitTask() {
       this.exit = false;
   }
    @Override
    public Boolean run() {
        return false;
    }
}
