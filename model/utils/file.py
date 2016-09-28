import os
import zipfile


class File(object):

    @staticmethod
    def save_data_into_1_file(path_to_input_folder, dataset_name, output_folder):
        """
        Args:
            path_to_input_folder:
            dataset_name:
            output_folder:

        Returns:
            A zipped file at output_folder/dataset_name.zip with all the files in path_to_input_folder.
        """
        path_to_output_folder = output_folder + '/' + dataset_name + '.zip'
        print('main(' + path_to_input_folder + ', ' + dataset_name + ', ' + output_folder + ')')
        zip_handler = zipfile.ZipFile(path_to_output_folder, 'w', zipfile.ZIP_DEFLATED)
        File.__zip_folder(path_to_input_folder, zip_handler)
        zip_handler.close()

    @staticmethod
    def __zip_folder(path, zip_handler):
        for root, dirs, files in os.walk(path):
            for file in files:
                zip_handler.write(os.path.join(root, file))
