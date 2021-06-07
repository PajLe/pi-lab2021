using SolrNet.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SolrNetPrimer
{
    // Objekat za prosleđivanje dokumenata Solr-u prilikom indeksiranja i 
    // preuzimanje rezultata nakon pretraživanja. 
    class Knjiga
    {
        // Objekat treba da sadrži javne property-je sa get i set delom. 
        // Property se mapira na odgovarajuće Solr polje atributom SolrField. 
        [SolrField("id")]
        public string Id { get; set; }

        [SolrField("link")]
        public List<string> Link { get; set; } // mora list zbog multi-valued

        [SolrField("fullpath")]
        public List<string> Fullpath { get; set; }

        [SolrField("size")]
        public long Size { get; set; }

        [SolrField("body")]
        public String Body { get; set; }

        public override string ToString()
        {
            return Id + " " + Link + " " + Size;
        }
    }
}
