namespace SolrNetPrimer
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnPretrazivanje = new System.Windows.Forms.Button();
            this.btnIndeksiranje = new System.Windows.Forms.Button();
            this.lblPolje = new System.Windows.Forms.Label();
            this.txtUpit = new System.Windows.Forms.TextBox();
            this.lblUpit = new System.Windows.Forms.Label();
            this.dgvResults = new System.Windows.Forms.DataGridView();
            this.cmbPolje = new System.Windows.Forms.ComboBox();
            this.prev_page_button = new System.Windows.Forms.Button();
            this.next_page_button = new System.Windows.Forms.Button();
            this.total_res_lab = new System.Windows.Forms.Label();
            this.res_count_lab = new System.Windows.Forms.Label();
            this.curr_page_lab = new System.Windows.Forms.Label();
            this.bracket_lab = new System.Windows.Forms.Label();
            this.total_pages_lab = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dgvResults)).BeginInit();
            this.SuspendLayout();
            // 
            // btnPretrazivanje
            // 
            this.btnPretrazivanje.Location = new System.Drawing.Point(314, 51);
            this.btnPretrazivanje.Margin = new System.Windows.Forms.Padding(2);
            this.btnPretrazivanje.Name = "btnPretrazivanje";
            this.btnPretrazivanje.Size = new System.Drawing.Size(112, 26);
            this.btnPretrazivanje.TabIndex = 0;
            this.btnPretrazivanje.Text = "Pretraživanje";
            this.btnPretrazivanje.UseVisualStyleBackColor = true;
            this.btnPretrazivanje.Click += new System.EventHandler(this.btnPretrazivanje_Click);
            // 
            // btnIndeksiranje
            // 
            this.btnIndeksiranje.Location = new System.Drawing.Point(9, 10);
            this.btnIndeksiranje.Margin = new System.Windows.Forms.Padding(2);
            this.btnIndeksiranje.Name = "btnIndeksiranje";
            this.btnIndeksiranje.Size = new System.Drawing.Size(112, 26);
            this.btnIndeksiranje.TabIndex = 1;
            this.btnIndeksiranje.Text = "Indeksiranje";
            this.btnIndeksiranje.UseVisualStyleBackColor = true;
            this.btnIndeksiranje.Click += new System.EventHandler(this.btnIndeksiranje_Click);
            // 
            // lblPolje
            // 
            this.lblPolje.AutoSize = true;
            this.lblPolje.Location = new System.Drawing.Point(9, 58);
            this.lblPolje.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.lblPolje.Name = "lblPolje";
            this.lblPolje.Size = new System.Drawing.Size(33, 13);
            this.lblPolje.TabIndex = 2;
            this.lblPolje.Text = "Polje:";
            // 
            // txtUpit
            // 
            this.txtUpit.Location = new System.Drawing.Point(203, 55);
            this.txtUpit.Margin = new System.Windows.Forms.Padding(2);
            this.txtUpit.Name = "txtUpit";
            this.txtUpit.Size = new System.Drawing.Size(76, 20);
            this.txtUpit.TabIndex = 5;
            // 
            // lblUpit
            // 
            this.lblUpit.AutoSize = true;
            this.lblUpit.Location = new System.Drawing.Point(166, 58);
            this.lblUpit.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.lblUpit.Name = "lblUpit";
            this.lblUpit.Size = new System.Drawing.Size(29, 13);
            this.lblUpit.TabIndex = 4;
            this.lblUpit.Text = "Upit:";
            // 
            // dgvResults
            // 
            this.dgvResults.AllowUserToAddRows = false;
            this.dgvResults.AllowUserToDeleteRows = false;
            this.dgvResults.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.dgvResults.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvResults.Location = new System.Drawing.Point(0, 104);
            this.dgvResults.Margin = new System.Windows.Forms.Padding(2);
            this.dgvResults.Name = "dgvResults";
            this.dgvResults.ReadOnly = true;
            this.dgvResults.RowHeadersWidth = 51;
            this.dgvResults.RowTemplate.Height = 24;
            this.dgvResults.Size = new System.Drawing.Size(723, 382);
            this.dgvResults.TabIndex = 6;
            // 
            // cmbPolje
            // 
            this.cmbPolje.FormattingEnabled = true;
            this.cmbPolje.Items.AddRange(new object[] {
            "body",
            "id",
            "link",
            "size",
            "fullpath"});
            this.cmbPolje.Location = new System.Drawing.Point(46, 52);
            this.cmbPolje.Margin = new System.Windows.Forms.Padding(2);
            this.cmbPolje.Name = "cmbPolje";
            this.cmbPolje.Size = new System.Drawing.Size(92, 21);
            this.cmbPolje.TabIndex = 7;
            // 
            // prev_page_button
            // 
            this.prev_page_button.Location = new System.Drawing.Point(143, 504);
            this.prev_page_button.Margin = new System.Windows.Forms.Padding(2);
            this.prev_page_button.Name = "prev_page_button";
            this.prev_page_button.Size = new System.Drawing.Size(112, 26);
            this.prev_page_button.TabIndex = 8;
            this.prev_page_button.Text = "Prethodna strana";
            this.prev_page_button.UseVisualStyleBackColor = true;
            this.prev_page_button.Click += new System.EventHandler(this.prev_page_button_Click);
            // 
            // next_page_button
            // 
            this.next_page_button.Location = new System.Drawing.Point(415, 501);
            this.next_page_button.Margin = new System.Windows.Forms.Padding(2);
            this.next_page_button.Name = "next_page_button";
            this.next_page_button.Size = new System.Drawing.Size(112, 26);
            this.next_page_button.TabIndex = 9;
            this.next_page_button.Text = "Sledeća strana";
            this.next_page_button.UseVisualStyleBackColor = true;
            this.next_page_button.Click += new System.EventHandler(this.next_page_button_Click);
            // 
            // total_res_lab
            // 
            this.total_res_lab.AutoSize = true;
            this.total_res_lab.Location = new System.Drawing.Point(567, 508);
            this.total_res_lab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.total_res_lab.Name = "total_res_lab";
            this.total_res_lab.Size = new System.Drawing.Size(88, 13);
            this.total_res_lab.TabIndex = 10;
            this.total_res_lab.Text = "Ukupno rezultata";
            // 
            // res_count_lab
            // 
            this.res_count_lab.AutoSize = true;
            this.res_count_lab.Location = new System.Drawing.Point(659, 508);
            this.res_count_lab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.res_count_lab.Name = "res_count_lab";
            this.res_count_lab.Size = new System.Drawing.Size(13, 13);
            this.res_count_lab.TabIndex = 11;
            this.res_count_lab.Text = "0";
            // 
            // curr_page_lab
            // 
            this.curr_page_lab.AutoSize = true;
            this.curr_page_lab.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.curr_page_lab.Location = new System.Drawing.Point(287, 496);
            this.curr_page_lab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.curr_page_lab.Name = "curr_page_lab";
            this.curr_page_lab.Size = new System.Drawing.Size(29, 31);
            this.curr_page_lab.TabIndex = 12;
            this.curr_page_lab.Text = "0";
            // 
            // bracket_lab
            // 
            this.bracket_lab.AutoSize = true;
            this.bracket_lab.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bracket_lab.Location = new System.Drawing.Point(331, 496);
            this.bracket_lab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.bracket_lab.Name = "bracket_lab";
            this.bracket_lab.Size = new System.Drawing.Size(22, 31);
            this.bracket_lab.TabIndex = 13;
            this.bracket_lab.Text = "/";
            // 
            // total_pages_lab
            // 
            this.total_pages_lab.AutoSize = true;
            this.total_pages_lab.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.total_pages_lab.Location = new System.Drawing.Point(364, 496);
            this.total_pages_lab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.total_pages_lab.Name = "total_pages_lab";
            this.total_pages_lab.Size = new System.Drawing.Size(29, 31);
            this.total_pages_lab.TabIndex = 14;
            this.total_pages_lab.Text = "0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(724, 547);
            this.Controls.Add(this.total_pages_lab);
            this.Controls.Add(this.bracket_lab);
            this.Controls.Add(this.curr_page_lab);
            this.Controls.Add(this.res_count_lab);
            this.Controls.Add(this.total_res_lab);
            this.Controls.Add(this.next_page_button);
            this.Controls.Add(this.prev_page_button);
            this.Controls.Add(this.cmbPolje);
            this.Controls.Add(this.dgvResults);
            this.Controls.Add(this.txtUpit);
            this.Controls.Add(this.lblUpit);
            this.Controls.Add(this.lblPolje);
            this.Controls.Add(this.btnIndeksiranje);
            this.Controls.Add(this.btnPretrazivanje);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dgvResults)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnPretrazivanje;
        private System.Windows.Forms.Button btnIndeksiranje;
        private System.Windows.Forms.Label lblPolje;
        private System.Windows.Forms.TextBox txtUpit;
        private System.Windows.Forms.Label lblUpit;
        private System.Windows.Forms.DataGridView dgvResults;
        private System.Windows.Forms.ComboBox cmbPolje;
        private System.Windows.Forms.Button prev_page_button;
        private System.Windows.Forms.Button next_page_button;
        private System.Windows.Forms.Label total_res_lab;
        private System.Windows.Forms.Label res_count_lab;
        private System.Windows.Forms.Label curr_page_lab;
        private System.Windows.Forms.Label bracket_lab;
        private System.Windows.Forms.Label total_pages_lab;
    }
}

